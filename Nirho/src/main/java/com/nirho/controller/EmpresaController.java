package com.nirho.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.DetalleEmpresaDTO;
import com.nirho.dto.RegistroEmpresaDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRH;
import com.nirho.model.CuestionarioEmpresaIRHTema;
import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.model.Proyecto;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;
import com.nirho.service.CuestionarioEmpresaIRHService;
import com.nirho.service.EmpresaService;
import com.nirho.util.ReporteUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/empresa" )
public class EmpresaController {

	public final static Logger logger = Logger.getLogger(EmpresaController.class);
	
	@Autowired
	EmpresaService empresaService;
	@Autowired
	CuestionarioEmpresaIRHService cuestionarioEmpresaServiceIRH;
	
	@GetMapping(value = "/todas")
	public List<Empresa> todas() throws NirhoControllerException{
		List<Empresa> empresa = new ArrayList<>();
		try {
			empresa = empresaService.obtenerEmpresasTodas();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return empresa;
	}
	
	@GetMapping(value = "/porRfc")
	public Empresa porRfc(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		Empresa empresa = new Empresa();
		try {
			empresa = empresaService.obtenerEmpresaPorRfc(rfc);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return empresa;
	}

	@PostMapping(value = "/registrar")
	public void registrarEmpresa(@Valid @RequestBody Empresa empresa) throws NirhoControllerException {
		logger.info(" ********************************* empresa a insertar/actualizar [" + empresa + "] *****************************");
		try {
			empresaService.registraEmpresa(empresa);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar la empresa en la BD");
		}
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public void eliminarEmpresa(@RequestBody Empresa empresa) throws NirhoControllerException {
		logger.info(" ********************************* empresa a eliminar [" + empresa + "] *****************************");
		try {
			empresaService.eliminarEmpresa(empresa);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al eliminar la empresa en la BD");
		}
	}
	
	@PostMapping(value = "/registrarEmpresaIRH")
	public void registrarEmpresaIRH(@RequestBody RegistroEmpresaDTO registroEmpresaDTO) throws NirhoControllerException {
		try {
			empresaService.registrarEmpresaIRH(registroEmpresaDTO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar la empresa IRH en la BD");
		}
	}
	
	@GetMapping(value = "/consultarEmpresaIRHRfc")
	public DetalleEmpresaDTO consultarEmpresaIRHRfc(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		DetalleEmpresaDTO detEmpresa = new DetalleEmpresaDTO();
		Entrevistado entrevistadoEmpresa = new Entrevistado();
		try {
			entrevistadoEmpresa = empresaService.consultarEmpresaIRH(rfc);
			detEmpresa.setEntrevistado(entrevistadoEmpresa);
			detEmpresa.setEmpresa(entrevistadoEmpresa.getEmpresa());
		}catch (NoResultException e) {
			throw new NoResultException("No se encontraron resultados con para el RFC");
		}catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return detEmpresa;
	}


	@GetMapping(value = "/solicitadoEvaluacion")
	public List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoControllerException{
		List<VwEmpresasSolicitudCuestionario> empresas = new ArrayList<>();
		try {
			empresas = empresaService.getEmpresasSolicitadoEvaluacion();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener empresas que han solicitado evaluacion");
		}
		return empresas;
	}
	
	
	@RequestMapping(value = "/reporte", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporte(@RequestParam(name="idEmpresa") Integer idEmpresa, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteAPO.docx"));

	        Empresa empresa = empresaService.obtenerEmpresaPorId(idEmpresa);
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", empresa.getEmpresa());
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de la empresa");
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de score");
	        	
	        if(x1 != null){	 
	        	
	        	XWPFTableRow row = x1.getRow(0);	    	            	
    			row.getCell(1).setText(empresa.getEmpresa());
    			row = x1.getRow(1);	    	            	
    			row.getCell(1).setText(empresa.getDireccion());
    			row = x1.getRow(2);	    	            	
    			row.getCell(1).setText(empresa.getRfc());
    			row = x1.getRow(3);	    	            	
    			row.getCell(1).setText(empresa.getGiro() + "");
    			row = x1.getRow(4);	    	            	
    			row.getCell(1).setText(empresa.getPais() + "");
    			row = x1.getRow(5);	    	            	
    			row.getCell(1).setText(empresa.getAnioInicioOperaciones() + "");
    			row = x1.getRow(6);	    	            	
    			row.getCell(1).setText(empresa.getFacturacionAnual() + "");
    			row = x1.getRow(7);	    	            	
    			row.getCell(1).setText(empresa.getProductoServicioEstrella() + "");
    			row = x1.getRow(8);	    	            	
    			row.getCell(1).setText(empresa.getPrincipalesProductosServicios() + "");
    			row = x1.getRow(9);	    	            	
    			row.getCell(1).setText(empresa.getNoEmpleadosAdministrativo() + "");
    			row = x1.getRow(10);	    	            	
    			row.getCell(1).setText(empresa.getNoEmpleadosOperativo() + "");
    			row = x1.getRow(11);	    	            	
    			row.getCell(1).setText(empresa.getTipoContratacionEmpleados() + "");
    			
	        }
	        
	        
	        HashMap<String, Double> datosGrafica = new HashMap<>();
	        String maxTema = "", minTema = "";
	        double sumatoriaScores = 0.0, minScore = 0.0, maxScore = 0.0;
	        int numScores = 0;
	        
	        List<CuestionarioEmpresaIRH> l = cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHByEmpresaId(idEmpresa);
	        
	        boolean firstData = true;
        	for(CuestionarioEmpresaIRHTema tema : l.get(0).getTemas()) { 
        		
        		datosGrafica.put(tema.getNombre(), tema.getScore());
        		sumatoriaScores += tema.getScore();
        		numScores++;
        		
        		if(firstData) {
        			maxTema = tema.getNombre();
	    			maxScore = tema.getScore();
	    			minTema = tema.getNombre();
	    			minScore = tema.getScore();
	    			firstData = false;
        		}
        		
        		if(tema.getScore() > maxScore) {
        			maxScore = tema.getScore();
        			maxTema = tema.getNombre();
        		}
        		
        		if(tema.getScore() < minScore) {
        			minScore = tema.getScore();
        			minTema = tema.getNombre();
        		}
        		
        	}
        	
        	  
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText(Math.round((sumatoriaScores / numScores)  * 100.0) / 100.0 + "");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Tema: " + maxTema + "\n, Score: " + maxScore);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Tema: " + minTema + ", Score: " + minScore);
	        }
	           
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                
	            	chart = (XWPFChart) part;  
	                String title= chart.getTitle().getBody().getParagraph(0).getText();
	                
	                if(title.equals("Comparativo del promedio de scores de la empresa respecto a los temas")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1; 
		 	             for(String key: datosGrafica.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datosGrafica.get(key));
	    	            	row.createCell(2).setCellValue(Math.round((sumatoriaScores / numScores)  * 100.0) / 100.0);
		 	            	i++;
		 	             }
	                }
	                
	                if(title.equals("Score por tema")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
	                	 Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1; 
		 	             for(String key: datosGrafica.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datosGrafica.get(key));
		 	            	i++;
		 	             }
	                }
	                
	            }
	        }
	        
	        String nombreReporte = "ReporteIRH_" + empresa.getEmpresa() + ".docx";
	        
	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=" + nombreReporte);
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al generar reporte");
		}
	}
	
}
