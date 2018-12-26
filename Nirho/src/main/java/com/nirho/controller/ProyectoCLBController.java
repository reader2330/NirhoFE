package com.nirho.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
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
import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.dto.PeriodoProyecto;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.Participante;
import com.nirho.model.Proyecto;
import com.nirho.service.CatalogoService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.GraficasProyectoService;
import com.nirho.service.ParticipanteService;
import com.nirho.service.ProyectoService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/proyectoCLB" )
public class ProyectoCLBController {
	public final static Logger logger = Logger.getLogger(ProyectoCLBController.class);
	public final static Integer ID_MODULO = 1;
	
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	CatalogoService catalogoService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	GraficasProyectoService graficasService;
	@Autowired
	ParticipanteService participanteService;
	@Autowired
	CuestionarioParticipanteDAO cuestPartService;
	
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
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteAPO.docx"));
			XWPFDocument document = new XWPFDocument(OPCPackage.open("C:/Users/DELL/Documents/NIRHO/jboss/jboss-eap-7.1/standalone/deployments/reporteCLB.docx")); 
			//ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", proyecto.getIdEmpresa().getEmpresa());
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Cumplimiento por area");
	        logger.info(" ********************************* x1 [" + x1 + "] *****************************");	        
	        	        
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de la empresa");
	        	        
	        List<Participante> participantes = participanteService.obtenerParticipantes(idProyecto);
	        logger.info(" ********************************* participantes [" + participantes + "] *****************************");
	        
	        int maxCalificacion = 5, minCalificacion = 1;
	        String maxArea = "", minArea = "", maxFuncion = "", minFuncion = "";
	        int promedio = 0;
	        
	        HashMap<String, Integer> datos = new HashMap<>(); 
	        
	        if(x1 != null){	             
	            for(int i = 1; i < participantes.size(); i++){
	            	
	            	String area = participantes.get(i - 1).getAreaOrg();
	            		      
	            	int promedioArea = 0;
	            	int numCalificacionesArea = 0;
	            	
	            	Participante p = participantes.get(i - 1);
	            	List<CuetionarioParticipante> cuestPartList = cuestPartService.findByParticipanteProyecto(p.getParticipantePK().getIdParticipante(), idProyecto);
	            	
					for (CuetionarioParticipante cuestPart : cuestPartList) {
						
						int respuesta = cuestPart.getRespuesta() != null?cuestPart.getRespuesta().intValue():0;
						
						XWPFTableRow row = null;
						if (i > 1) {
							row = x1.createRow();
						} else {
							row = x1.getRow(i);
						}

						row.getCell(0).setText(area);
						row.getCell(1).setText(cuestPart.getPregunta().getEnunciado());
						row.getCell(2).setText(respuesta + "");
						if (respuesta >= maxCalificacion) {
							maxArea = area;
							maxFuncion = cuestPart.getPregunta().getEnunciado();
							maxCalificacion = respuesta;
						}
						if (respuesta <= minCalificacion) {
							minArea = area;
							minFuncion = cuestPart.getPregunta().getEnunciado();
							minCalificacion = respuesta;
						}
						promedio += respuesta;
						promedioArea += respuesta;
						numCalificacionesArea++;
					}
	            	
	            	promedioArea = Math.round(promedioArea / numCalificacionesArea);
	            	datos.put(area, promedioArea);
	            	
	            }
	        }
	        
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText(promedio + "");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Area: " + maxArea + "\n, Cuestión: " + maxFuncion + ", Promedio: " + maxCalificacion);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Area: " + minArea + ", Cuestión: " + minFuncion + ", Promedio: " + minCalificacion);
	        }
	           
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                chart = (XWPFChart) part;  
	                if(chart.getTitle().toString().equals("Comparativo del promedio de la empresa respecto a las áreas")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datos.keySet()) {
		 	            	dataSheet2.getRow(i).getCell(0).setCellValue(key);
		 	            	dataSheet2.getRow(i).getCell(1).setCellValue(datos.get(key));
		 	            	dataSheet2.getRow(i).getCell(2).setCellValue(promedio);
		 	            	i++;
		 	             }
	                }
	            }
	        }
	        
	        String nombreReporte = "ReporteCLB_" + proyecto.getNombre() + ".docx";
	        
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
