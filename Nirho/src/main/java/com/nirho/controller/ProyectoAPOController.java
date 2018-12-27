package com.nirho.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Cell;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.PeriodoProyecto;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.model.ParticipanteAPOAmpActividad;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.model.Proyecto;
import com.nirho.service.EmailService;
import com.nirho.service.EmpresaService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.GraficasProyectoService;
import com.nirho.service.ParticipanteAPOService;
import com.nirho.service.ProyectoService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/proyectoAPO" )
public class ProyectoAPOController {
	public final static Logger logger = Logger.getLogger(ProyectoAPOController.class);
	public final static Integer ID_MODULO = 4;
	
	@Autowired
	ParticipanteAPOService participanteAPOService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	private GraficasProyectoService graficasService;
	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(value = "/todos")
	public List<Proyecto> todos() throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosTodos(ID_MODULO);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
	
	@GetMapping(value = "/porConsultor")
	public List<Proyecto> porConsultor(@RequestParam(name="idUsuario") Integer idUsuario) throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			proyectos = proyectoService.obtenerProyectosConsultor(idUsuario, ID_MODULO);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
	
	@GetMapping(value = "/deConsultorEnSesion")
	public List<Proyecto> deConsultorEnSesion(HttpServletRequest request) throws NirhoControllerException{
		List<Proyecto> proyectos = new ArrayList<>();
		try {
			Long id = SessionUtil.getUsuarioSession(request).getId();
			proyectos = proyectoService.obtenerProyectosConsultor(id.intValue(), ID_MODULO);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return proyectos;
	}
			
	@RequestMapping(value = "/porId", method = RequestMethod.GET)
	@ResponseBody
	public Proyecto porId(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		Proyecto proyecto = new Proyecto();
		try {
			proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return proyecto;
	}
		
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	@ResponseBody
	public void registrarProyecto(@RequestBody Proyecto proyecto) throws NirhoControllerException {
		logger.info(" ********************************* proyecto [" + proyecto + "] *****************************");
		try {
			proyecto.setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_CAPTURA));
			proyectoService.registrarProyecto(proyecto, ID_MODULO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
		
	@RequestMapping(value = "/agignarPeriodoGarantia", method = RequestMethod.POST)
	@ResponseBody
	public void agignarPeriodoGarantia(@RequestBody PeriodoProyecto periodo) throws NirhoControllerException {
		logger.info(" ********************************* periodo [" + periodo + "] *****************************");
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");			
			try {
				periodo.getProyecto().setFechaRegistro(formatDate.parse(periodo.getFechaRegistro()));
				periodo.getProyecto().setFechaFin(formatDate.parse(periodo.getFechaFin()));
			} catch (ParseException e) {
				logger.info("ParserExceptio [" + e.getLocalizedMessage() +"]");
			}  catch (NullPointerException e) {
				logger.info("NullPointerExceptio [" + e.getLocalizedMessage() +"]");
			}
			periodo.getProyecto().setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_ASIGNADO));
			proyectoService.registrarProyecto(periodo.getProyecto(), ID_MODULO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/asignarConsultor", method = RequestMethod.POST)
	@ResponseBody
	public void asignarConsultor(@RequestBody ConsultorProyectoPK datos) throws NirhoControllerException {
		logger.info(" ********************************* ConsultorProyecto [" + datos + "] *****************************");
		try {
			proyectoService.asignarConsultor(datos);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el proyecto en la BD");
		}
	}
	
	@RequestMapping(value = "/estatus", method = RequestMethod.GET)
	@ResponseBody
	public EstatusProyecto estatus(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		EstatusProyecto estatus = new EstatusProyecto();
		try {
			estatus = proyectoService.obtenerProyectoPorId(idProyecto).getIdEstatus();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return estatus;
	}
	
	@RequestMapping(value = "/apertura", method = RequestMethod.GET)
	@ResponseBody
	public void apertura(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_VIGENTE);
			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	@RequestMapping(value = "/cierre", method = RequestMethod.GET)
	@ResponseBody
	public void cierre(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_FINALIZADO);
			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			graficasService.generarGraficasProyecto(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	@RequestMapping(value = "/revisionResultados", method = RequestMethod.GET)
	@ResponseBody
	public void revisionResultados(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
			EstatusProyecto estatus = estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_RESULTADOS);

			proyecto.setIdEstatus(estatus);
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al registrar el proyecto");
		}
	}
	
	@RequestMapping(value = "/reporte", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporte(@RequestParam(name="idProyecto") Integer idProyecto, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteAPO.docx"));

	        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", proyecto.getIdEmpresa().getEmpresa());
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Cumplimiento por area");
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de la empresa");
	        	        
	        List<ParticipanteAPO> participantes = participanteAPOService.obtenerParticipantes(idProyecto);
	        int maxCalificacion = 0, minCalificacion = 5;
	        String maxArea = "", minArea = "", maxFuncion = "", minFuncion = "";
	        double promedioGeneral = 0;
	        int numCalificacionesGeneral = 0;
	        int indexRow = 1;
	        
	        HashMap<String, Double> datos = new HashMap<>(); 
	        
	        if(x1 != null){	             
	            for(int i = 1; i < participantes.size(); i++){
	            	
	            	String area = participantes.get(i - 1).getAreaOrg();
	            	double promedioArea = 0;
	            	int numCalificacionesArea = 0;
	            	
	            	for(ParticipanteAPOAmp ampliacion: participantes.get(i -1).getAmpliaciones()) {
	            		for(ParticipanteAPOAmpFuncion funcion: ampliacion.getFunciones()) {
	            			
	            			XWPFTableRow row = null;
	    	            	if(indexRow > 1) {
	    	            		row = x1.createRow();
	    	            	} else {
	    	            		row = x1.getRow(indexRow);
	    	            	}
	    	            	
	            			row.getCell(0).setText(area);
	    	            	row.getCell(1).setText(funcion.getFuncion());
	    	            	row.getCell(2).setText(funcion.getCalificacion() + "");
	    	            	if(funcion.getCalificacion() >= maxCalificacion) {
	    	            		maxArea = area;
	    	            		maxFuncion = funcion.getFuncion();
	    	            		maxCalificacion = funcion.getCalificacion();
	    	            	}
	    	            	if(funcion.getCalificacion() <= minCalificacion) {
	    	            		minArea = area;
	    	            		minFuncion = funcion.getFuncion();
	    	            		minCalificacion = funcion.getCalificacion();
	    	            	}
	    	            	promedioGeneral += funcion.getCalificacion();
	    	            	numCalificacionesGeneral++;
	    	            	promedioArea += funcion.getCalificacion();
	    	            	numCalificacionesArea++;
	    	            	indexRow++;
		            	}
	            	}
	            	
	            	promedioArea = promedioArea / numCalificacionesArea;
	            	promedioArea = Math.round(promedioArea * 100.0) / 100.0;
	            	datos.put(area, promedioArea);
	            	
	            }
	        }
	        
	        promedioGeneral = promedioGeneral / numCalificacionesGeneral;
	        promedioGeneral = Math.round(promedioGeneral * 100.0) / 100.0;
	        
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText(promedioGeneral + "");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Area: " + maxArea + "\n, Función: " + maxFuncion + ", Promedio: " + maxCalificacion);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Area: " + minArea + ", Función: " + minFuncion + ", Promedio: " + minCalificacion);
	        }
	           
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                
	            	chart = (XWPFChart) part;  
	                String title= chart.getTitle().getBody().getParagraph(0).getText();
	                
	                if(title.equals("Comparativo del promedio de la empresa respecto a las áreas")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datos.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datos.get(key));
		 	            	row.createCell(2).setCellValue(promedioGeneral);
		 	            	i++;
		 	             }
	                }
	                
	                if(title.equals("Cumplimiento por área")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datos.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datos.get(key));
		 	            	i++;
		 	             }
	                }
	                
	            }
	        }
	        
	        String nombreReporte = "ReporteAPO_" + proyecto.getNombre() + ".docx";
	        
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
	
	
	@RequestMapping(value = "/reporte/participante", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporteIndividual(@RequestParam(name="idParticipante") Integer idParticipante, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteAPOIndividual.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\Alfredo\\elimina\\reporteAPOIndividual.docx"));

	        ParticipanteAPO participante = participanteAPOService.getOne(idParticipante);
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.participante", participante.getNombres() + " " + participante.getaPaterno() + " " + participante.getaMaterno());
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Tabla participante individual");
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen individual");

	        if(x1 != null){	   
	        	XWPFTableRow row1 = x1.getRow(0);
	        	row1.getCell(1).setText(participante.getNombres() + " " + participante.getaPaterno() + " " + participante.getaMaterno());
	        	XWPFTableRow row2 = x1.getRow(1);
	        	row2.getCell(1).setText(participante.getAreaOrg());
	        	XWPFTableRow row3 = x1.getRow(2);
	        	row3.getCell(1).setText(participante.getPuesto());
	        	XWPFTableRow row4 = x1.getRow(3);
	        	row4.getCell(1).setText(participante.getNivelTexto());
	        	XWPFTableRow row5 = x1.getRow(4);
	        	String objetivo = "";
	        	for (ParticipanteAPOAmp amp : participante.getAmpliaciones()) {
	        		objetivo = amp.getObjetivoPuesto();
	        	}        		
	        	row5.getCell(1).setText(objetivo);
	        }
	         
	        
	        HashMap<String, Double> datosGrafico = new HashMap<>(); 
	        double maxCumplimiento = 0.0, minCumplimiento = 5.0;
	        String maxCumplimientoActividad = "", minCumplimientoActividad = "", maxCumplimientoFuncion = "", minCumplimientoFuncion = "";
	        double promedioCumplimiento = 0;
	        int numCumplimientos = 0;
	        
	        List<String> headers = new ArrayList<>();
	        headers.add("Actividad");
	        headers.add("Nombre de la meta");
	        headers.add("Indicador");
	        headers.add("Tiempo");
	        headers.add("Programado");
	        headers.add("Real");
	        headers.add("% Cumplimiento");
	        
	        int index = 0;
	        boolean firstData = true;
	        for (ParticipanteAPOAmp amp : participante.getAmpliaciones()) {
	        	for (ParticipanteAPOAmpFuncion funcion : amp.getFunciones()) {
	        		List<List<String>> content = new ArrayList<>();
	        		for (ParticipanteAPOAmpActividad act : funcion.getActividades()) {
	        			List<String> datos = new ArrayList<>();
	        			datos.add(act.getNombre());
	        			datos.add(funcion.getMetaKpi());
	        			datos.add(funcion.getCantidadMeta());
	        			datos.add(funcion.getTiempo());
	        			datos.add("5");
	        			datos.add(act.getCalificacion() + "");
	        			double auxPromedioCumplimiento = ((act.getCalificacion()*100) / 5);
	        			datos.add(auxPromedioCumplimiento + "");
	        			content.add(datos);
	        			numCumplimientos++;
	        			promedioCumplimiento += auxPromedioCumplimiento; 

	        			if(firstData) {
	        				maxCumplimiento = auxPromedioCumplimiento;
	        				maxCumplimientoActividad = act.getNombre();
	        				maxCumplimientoFuncion = funcion.getFuncion();
	        				minCumplimiento = auxPromedioCumplimiento;
	        				minCumplimientoActividad = act.getNombre();
	        				minCumplimientoFuncion = funcion.getFuncion();
	            		}
	            		
	            		if(auxPromedioCumplimiento > maxCumplimiento) {
	            			maxCumplimiento = auxPromedioCumplimiento;
	        				maxCumplimientoActividad = act.getNombre();
	        				maxCumplimientoFuncion = funcion.getFuncion();
	            		}
	            		
	            		if(auxPromedioCumplimiento < minCumplimiento) {
	            			minCumplimiento = auxPromedioCumplimiento;
	        				minCumplimientoActividad = act.getNombre();
	        				minCumplimientoFuncion = funcion.getFuncion();
	            		}
	        			
	            		datosGrafico.put(act.getNombre(), auxPromedioCumplimiento);
	            		
	        		}   
	        		
	        		if(!content.isEmpty()) {
	        			ReporteUtil.agregarParrafo(document, " ");
	        			ReporteUtil.crearTablaFuncion(document, funcion.getFuncion(), headers, content);
	        		}
	        	}  
        	}  
	        
	        promedioCumplimiento = promedioCumplimiento / numCumplimientos;
	        promedioCumplimiento = Math.round(promedioCumplimiento * 100.0) / 100.0;
	        
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText(promedioCumplimiento + "");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Función: " + maxCumplimientoFuncion + "\n, Actividad: " + maxCumplimientoActividad + ", Cumplimiento: " + maxCumplimiento);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Función: " + minCumplimientoFuncion + "\n, Actividad: " + minCumplimientoActividad + ", Cumplimiento: " + minCumplimiento);
	        }
	           
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                
	            	chart = (XWPFChart) part;  
	                String title= chart.getTitle().getBody().getParagraph(0).getText();
	                
	                if(title.equals("Comparativo de cumplimientos")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datosGrafico.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datosGrafico.get(key));
		 	            	row.createCell(2).setCellValue(promedioCumplimiento);
		 	            	i++;
		 	             }
	                }
	                
	                if(title.equals("Cumplimiento")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datosGrafico.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datosGrafico.get(key));
		 	            	i++;
		 	             }
	                }
	                
	            }
	        }
	        
	        String nombreReporte = "ReporteAPO_" + participante.getNombres() + " " + participante.getaPaterno() + " " + participante.getaMaterno() + ".docx";
	        
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
