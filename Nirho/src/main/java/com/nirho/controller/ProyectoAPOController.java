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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.nirho.dto.PeriodoProyecto;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
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
			
			URL reporteAPOURL = ProyectoAPOController.class.getClassLoader().getResource("plantillaReporteAPO.docx");
			
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open(reporteAPOURL.getPath())); 
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        
	        Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", proyecto.getIdEmpresa().getEmpresa());
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Cumplimiento por area");
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de la empresa");
	        
	        
	        List<ParticipanteAPO> participantes = participanteAPOService.obtenerParticipantes(idProyecto);
	        int maxCalificacion = 0, minCalificacion = 5;
	        String maxArea = "", minArea = "", maxFuncion = "", minFuncion = "";
	        
	        if(x1 != null){	             
	            for(int i = 1; i < participantes.size(); i++){
	            	
	            	XWPFTableRow row = null;
	            	if(i > 1) {
	            		row = x1.createRow();
	            	} else {
	            		row = x1.getRow(i);
	            	}
	            	
	            	String area = participantes.get(i - 1).getAreaOrg();
	            	
	            	for(ParticipanteAPOAmp ampliacion: participantes.get(i -1).getAmpliaciones()) {
	            		for(ParticipanteAPOAmpFuncion funcion: ampliacion.getFunciones()) {
	            			row.getCell(0).setText(area);
	    	            	row.getCell(1).setText(funcion.getFuncion());
	    	            	row.getCell(2).setText(funcion.getCalificacion() + "");
	    	            	if(funcion.getCalificacion() > maxCalificacion) {
	    	            		maxArea = area;
	    	            		maxFuncion = funcion.getFuncion();
	    	            		maxCalificacion = funcion.getCalificacion();
	    	            	}
	    	            	if(funcion.getCalificacion() < minCalificacion) {
	    	            		minArea = area;
	    	            		minFuncion = funcion.getFuncion();
	    	            		minCalificacion = funcion.getCalificacion();
	    	            	}
		            	}
	            	}
	            	
	            }
	        }
	        
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText("Promedio");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Area: " + maxArea + ", Función: " + maxFuncion + ", Promedio: " + maxCalificacion);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Area: " + minArea + ", Función: " + minFuncion + ", Promedio: " + minCalificacion);
	        }
	           
	        XWPFChart chart = ReporteUtil.getGraficoPorTitulo(document, "Comparativo del promedio de la empresa respecto a las áreas");
	        XSSFWorkbook wb2 = chart.getWorkbook();
	        Sheet dataSheet2 = wb2.getSheetAt(0);
	        System.out.println(dataSheet2.getRow(2).getCell(2));
	        dataSheet2.getRow(2).getCell(2).setCellValue(99);

	        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); 
	        response.setHeader("Content-Disposition", "attachment; filename=test.docx");
	        document.write(response.getOutputStream());
	   
	        response.flushBuffer();

		} catch(IOException | InvalidFormatException e){
			throw new NirhoControllerException("Problemas al generar reporte");
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al generar reporte");
		}
	}
	
}
