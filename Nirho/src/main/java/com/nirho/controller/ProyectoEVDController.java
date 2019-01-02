package com.nirho.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.AcumDTO;
import com.nirho.dto.AreaPromDTO;
import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.dto.GraficaRespPregDTO;
import com.nirho.dto.GraficaResultadoDTO;
import com.nirho.dto.PeriodoProyecto;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.Opcion;
import com.nirho.model.Participante;
import com.nirho.model.ParticipantePK;
import com.nirho.model.Proyecto;
import com.nirho.service.CuestionarioParticipanteService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.GraficasProyectoService;
import com.nirho.service.ParticipanteService;
import com.nirho.service.ProyectoService;
import com.nirho.util.ReporteUtil;
import com.nirho.util.SessionUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/proyectoEVD" )
public class ProyectoEVDController {
	public final static Logger logger = Logger.getLogger(ProyectoEVDController.class);
	public final static Integer ID_MODULO = 2;
	
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	private GraficasProyectoService graficasService;
	@Autowired
	private ParticipanteService participanteService;
	@Autowired
	private CuestionarioParticipanteService cuestPartService;
	
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
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteEVD.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteEVD.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:/Users/DELL/Documents/NIRHO/jboss/jboss-eap-7.1/standalone/deployments/reporteEVD.docx")); 
			
			GraficaRespPregDTO resGraficas = graficasService.obtenerGraficasRespuestasPreguntas(idProyecto);
		    logger.info(" ********************************* GraficaRespPregDTO [" + resGraficas + "] *****************************");
			
	        Proyecto proyecto = resGraficas.getProyecto();
	        
	        ReporteUtil.reemplazarParrafo(document, "nombre.empresa", proyecto.getIdEmpresa().getEmpresa());
	        
	        XWPFTable x0 =  ReporteUtil.getTablaPorTitulo(document, "Categorias por area");
	        
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Cumplimiento por area");
	        	        
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "Resumen de la empresa");
	        	        
	        List<GraficaAreaOrgDTO> resArea = resGraficas.getAreas();
	        logger.info(" ********************************* graficaAreaOrgList [" + resArea + "] *****************************");
	        
	        Map<String, AcumDTO> areasMap = new HashMap<>();
	        for (GraficaAreaOrgDTO gaDTO : resArea) {
	        	List<GraficaResultadoDTO> graficaResultadoList = gaDTO.getResultados();
	        	for (GraficaResultadoDTO resul : graficaResultadoList) {
	        		int respuesta = (resul.getNumResp1()*1 + resul.getNumResp2()*2 +
							resul.getNumResp3()*3 + resul.getNumResp4()*4 + resul.getNumResp5()*5);
	        		if(respuesta != 0) {
	        			if(areasMap.get(gaDTO.getAreaOrg())!=null){
		        			areasMap.get(gaDTO.getAreaOrg()).setCont(areasMap.get(gaDTO.getAreaOrg()).getCont()+1);
		        			areasMap.get(gaDTO.getAreaOrg()).setSuma(areasMap.get(gaDTO.getAreaOrg()).getSuma()+respuesta);
		        		} else {
		        			AcumDTO acum = new AcumDTO(respuesta,1);
		        			areasMap.put(gaDTO.getAreaOrg(), acum);
		        		}
	        		}
	        	}
	        }
	        
	        if(areasMap.size() == 0) {
	        	logger.info("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        	throw new NirhoControllerException("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        }
	        
	        List<AreaPromDTO> promAreas = new ArrayList<>();
	        for (Map.Entry<String, AcumDTO> entry : areasMap.entrySet()) {
	        	double promedio = Math.round(((double) entry.getValue().getSuma()/entry.getValue().getCont()));
	        	AreaPromDTO areaProm = new AreaPromDTO(entry.getKey(), promedio);
				logger.info(areaProm);
				promAreas.add(areaProm);
			}
	        
	        for (int i = 0; i < promAreas.size() - 1; i++) {
				for (int j = 0; j < promAreas.size() - 1; j++) {
					if (promAreas.get(j).getProm() < promAreas.get(j + 1).getProm()) {
						AreaPromDTO tmp = promAreas.get(j + 1);
						promAreas.set(j + 1, promAreas.get(j));
						promAreas.set(j, tmp);
					}
				}
			}
	        
	        boolean primerRow = true;
	        	        
			if (x0 != null) {
				for (AreaPromDTO areaProm : promAreas) {
					XWPFTableRow row = null;
					if (primerRow) {
						row = x0.getRow(1);
						primerRow = false;
					} else {
						row = x0.createRow();
					}
					row.getCell(0).setText(areaProm.getArea());
					row.getCell(1).setText("" + areaProm.getProm());
				}
			}
	        
	        int maxCalificacion = 1, minCalificacion = 1;
	        String maxArea = "", minArea = "", maxFuncion = "", minFuncion = "";
	        double promedioGeneral = 0;
	        int numCalificacionesGeneral = 0;
	        primerRow = true;
	        
	        HashMap<String, Double> datos = new HashMap<>(); 
	        
	        if(x1 != null){	            
	        	        	
	            for(GraficaAreaOrgDTO gaDTO: resArea){
	            	
	            	String area = gaDTO.getAreaOrg();
	            		      
	            	double promedioArea = 0;
	            	int numCalificacionesArea = 0;
	            	
	            	XWPFTableRow row = null;
									
	            	List<GraficaResultadoDTO> graficaResultadoList = gaDTO.getResultados();
	            	
					for (GraficaResultadoDTO resul: graficaResultadoList) {				
						int respuesta = (resul.getNumResp1()*1 + resul.getNumResp2()*2 +
								resul.getNumResp3()*3 + resul.getNumResp4()*4 + resul.getNumResp5()*5);
						if(respuesta != 0) {
							if (primerRow) {
								row = x1.getRow(1);
								primerRow = false;
							} else {
								row = x1.createRow();
							}
							row.getCell(0).setText(area);
							row.getCell(1).setText(resul.getPregunta().getEnunciado());												
							row.getCell(2).setText(respuesta + "");
							
							if (respuesta >= maxCalificacion) {
								maxArea = area;
								maxFuncion = resul.getPregunta().getEnunciado();
								maxCalificacion = respuesta;
							}
							if (respuesta <= minCalificacion) {
								minArea = area;
								minFuncion = resul.getPregunta().getEnunciado();
								minCalificacion = respuesta;
							}
							promedioGeneral += respuesta;
	    	            	numCalificacionesGeneral++;
	    	            	promedioArea += respuesta;
	    	            	numCalificacionesArea++;
						}
					}
	            	if(numCalificacionesArea != 0) {
	            		promedioArea = Math.round(promedioArea / numCalificacionesArea);
		            	datos.put(area, promedioArea);
	            	}
	            }
	        }
	        
	        if(numCalificacionesGeneral == 0) {
	        	logger.info("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        	throw new NirhoControllerException("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        }
	        
	        promedioGeneral = promedioGeneral / numCalificacionesGeneral;
	        	        	        
	        if(x2 != null){
	        	XWPFTableRow row1 = x2.getRow(0);
	        	row1.getCell(1).setText(promedioGeneral + "");
	        	XWPFTableRow row2 = x2.getRow(1);
	        	row2.getCell(1).setText("Area: " + maxArea + "\n, Competencia: " + maxFuncion + ", Promedio: " + maxCalificacion);
	        	XWPFTableRow row3 = x2.getRow(2);
	        	row3.getCell(1).setText("Area: " + minArea + ", Competencia: " + minFuncion + ", Promedio: " + minCalificacion);
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
	        
	        String nombreReporte = "ReporteEVD_" + proyecto.getNombre() + ".docx";
	        
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
	public void genearReporteIndividual(@RequestParam(name="idProyecto") Integer idProyecto, @RequestParam(name="idParticipante") Integer idParticipante,
			HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reporteEVDIndividual.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reporteEVDIndividual.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:/Users/DELL/Documents/NIRHO/jboss/jboss-eap-7.1/standalone/deployments/reporteEVDIndividual.docx"));

	        Participante participante = participanteService.obtenerParticipante(new ParticipantePK(idParticipante, idProyecto));
	        logger.info(" ********************************* participante [" + participante + "] *****************************");
	        
	        String apePa = participante.getAPaterno()!=null?participante.getAPaterno():"";
	        String apeMa = participante.getAMaterno()!=null?participante.getAMaterno():"";
	        String nom =  participante.getNombres()!=null?participante.getNombres():"";
	        String nombreParticipante = nom + " " + apePa + " " + apeMa; 
			
			logger.info(" ********************************* nombreParticipante [" + nombreParticipante + "] *****************************");
	        
			ReporteUtil.reemplazarParrafo(document, "nombre.participante", nombreParticipante);
	  
	        XWPFTable x1 =  ReporteUtil.getTablaPorTitulo(document, "Tabla participante individual");
	        XWPFTable x2 =  ReporteUtil.getTablaPorTitulo(document, "formato de medicion");
	        XWPFTable x3 =  ReporteUtil.getTablaPorTitulo(document, "competencias de desempeno");
	        XWPFTable x4 =  ReporteUtil.getTablaPorTitulo(document, "resultados de desempeno");
	        XWPFTable x5 =  ReporteUtil.getTablaPorTitulo(document, "resultados de competencias");
	        
	        if(x1 != null){	   
	        	XWPFTableRow row1 = x1.getRow(0);
	        	row1.getCell(1).setText(nombreParticipante);
	        	XWPFTableRow row2 = x1.getRow(1);
	        	row2.getCell(1).setText(participante.getAreaOrg());
	        	XWPFTableRow row3 = x1.getRow(2);
	        	row3.getCell(1).setText(participante.getPuesto());
	        	XWPFTableRow row4 = x1.getRow(3);
	        	row4.getCell(1).setText(participante.getNivelTexto());
	        	XWPFTableRow row5 = x1.getRow(4);
	        	String jefeDirecto = "";
	        	try {
	        		Participante jefe = participanteService.obtenerParticipante(new ParticipantePK(participante.getIdPartJefeInm(), idProyecto));
	        		String apePaJefe = jefe.getAPaterno()!=null?jefe.getAPaterno():"";
	    	        String apeMaJefe = jefe.getAMaterno()!=null?jefe.getAMaterno():"";
	    	        String nomJefe =  jefe.getNombres()!=null?jefe.getNombres():"";
	    	        jefeDirecto = nomJefe + " " + apePaJefe + " " + apeMaJefe; 
	    	        logger.info(" ********************************* jefeDirecto [" + jefeDirecto + "] *****************************");
	        	} catch(Exception e) {
	        		logger.info("Exception e [" + e.getMessage() + "]");
	        	}
	        	row5.getCell(1).setText(jefeDirecto);
	        }
	        
	        List<CuetionarioParticipante> factores = new ArrayList<>();
	        List<CuetionarioParticipante> competencias = new ArrayList<>();
	        List<CuetionarioParticipante> cuestPartList = cuestPartService.obtenerCuestionarioParticipante(idParticipante, idProyecto);
	        logger.info(" ********************************* cuestPartList [" + cuestPartList + "] *****************************");
	        HashMap<String, Integer> datos = new HashMap<>();
	        int promedioGeneral = 0;
	        int conta = 0;
	        for(CuetionarioParticipante cp: cuestPartList) {
	        	dispersionMock(cp); //dispersionMock para efectos de pruebas de reportes
	        	Integer promedio = Math.round(((cp.getRespuestaJefe() + cp.getRespuestaRh())/2));
	        	if(promedio.intValue() != 0) {
	        		datos.put(cp.getPregunta().getEnunciado(), promedio);
		        	promedioGeneral = promedioGeneral + Math.round(((cp.getRespuestaJefe() + cp.getRespuestaRh())/2));
		        	if(cp.getPregunta().getIdTema().getTipo()!=null && cp.getPregunta().getIdTema().getTipo().equals("FA")) {
		        		factores.add(cp);
		        		logger.info(" factor [" + cp + "]");
		        	} else {
		        		logger.info(" competencia [" + cp + "]");
		        		competencias.add(cp);
		        	}
		        	conta = conta + 1;
	        	}
	        }
	        
	        if(conta == 0) {
	        	logger.info("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        	throw new NirhoControllerException("************ ¡¡¡¡¡¡¡¡¡ sin registros para graficar !!!!!!!!!! *************");
	        }
	        
	        promedioGeneral = Math.round(promedioGeneral/conta);
	        
	        logger.info("********************************* promedioGeneral [" + promedioGeneral + "] *********************************");
	        logger.info("********************************* num factores [" + factores.size() + "] *********************************");
	        logger.info("********************************* num competencias [" + competencias.size() + "] *********************************");
	        
	        if(x2 != null) {
	        	XWPFTableRow row = null;
	        	for(CuetionarioParticipante cp: factores) {
	        		boolean primerRow = true;
		        	if (primerRow) {
		        		row = x2.getRow(1);
						primerRow = false;
					} else {
						row = x2.createRow();
					}
	        		row.getCell(0).setText(cp.getTema().getNombre());
	        		row.getCell(1).setText(cp.getTema().getDescripcion());
	        		List<Opcion> opciones = cuestPartService.opcionesTema(cp.getTema().getIdTema());
	        		logger.info(" *********factores*********** opciones [" + opciones + "] *****************************");
	        		String resJefe = "";
	        		switch(cp.getRespuestaJefe()) {
	        			case 1: resJefe = "BR"; break;
	        			case 2: resJefe = "MR"; break;
	        			case 3: resJefe = "R"; break;
	        			case 4: resJefe = "RS"; break;
	        			case 5: resJefe = "E"; break;
	        		}
	        		String resRH = "";
	        		switch(cp.getRespuestaRh()) {
	        			case 1: resRH = "BR"; break;
	        			case 2: resRH = "MR"; break;
	        			case 3: resRH = "R"; break;
	        			case 4: resRH = "RS"; break;
	        			case 5: resRH = "E"; break;
	        		}    
	        		for(Opcion op: opciones) {
	        			if(resJefe.equals(op.getTipo())) {
	        				row.getCell(2).setText(op.getEnunciado());
	        			}
	        			if(resRH.equals(op.getTipo())) {
	        				row.getCell(3).setText(op.getEnunciado());
	        			}
	        		}
	        	}
	        }
	        
	        if(x3 != null) {
	        	XWPFTableRow row = null;
	        	boolean primerRow = true;
	        	for(CuetionarioParticipante cp: competencias) {
	        		if (primerRow) {
		        		row = x3.getRow(1);
						primerRow = false;
					} else {
						row = x3.createRow();
					}
	        		row.getCell(0).setText(cp.getTema().getNombre());
	        		row.getCell(1).setText(cp.getTema().getDescripcion());
	        		List<Opcion> opciones = cuestPartService.opcionesTema(cp.getTema().getIdTema());
	        		logger.info(" *********competencias*********** opciones [" + opciones + "] *****************************");
	        		String resJefe = "";
	        		switch(cp.getRespuestaJefe()) {
	        			case 1: resJefe = "BR"; break;
	        			case 2: resJefe = "MR"; break;
	        			case 3: resJefe = "R"; break;
	        			case 4: resJefe = "RS"; break;
	        			case 5: resJefe = "E"; break;
	        		}
	        		String resRH = "";
	        		switch(cp.getRespuestaRh()) {
	        			case 1: resRH = "BR"; break;
	        			case 2: resRH = "MR"; break;
	        			case 3: resRH = "R"; break;
	        			case 4: resRH = "RS"; break;
	        			case 5: resRH = "E"; break;
	        		}    
	        		for(Opcion op: opciones) {
	        			if(resJefe.equals(op.getTipo())) {
	        				row.getCell(2).setText(op.getEnunciado());
	        			}
	        			if(resRH.equals(op.getTipo())) {
	        				row.getCell(3).setText(op.getEnunciado());
	        			}
	        		}
	        	}
	        }
	        
	        String factoresMejora = "";
	        String factoresSufis = "";
	        String factoresSobresa = "";
	        for(CuetionarioParticipante cp: factores) {
	        	int respJefe = cp.getRespuestaJefe()!=null?cp.getRespuestaJefe().intValue():0;
	        	int respRH = cp.getRespuestaRh()!=null?cp.getRespuestaRh().intValue():0;
	        	int promedio = (respJefe + respRH)/2;
	        	if(promedio > 0 && promedio<3) {
	        		factoresMejora = factoresMejora + (factoresMejora.length() != 0?", ":"") + cp.getPregunta().getEnunciado();
	        	} else if(promedio == 3) {
	        		factoresSufis = factoresSufis + (factoresSufis.length() != 0?", ":"") + cp.getPregunta().getEnunciado();
	        	} else if(promedio>3) {
	        		factoresSobresa = factoresSobresa + (factoresSobresa.length() != 0?",":"") + cp.getPregunta().getEnunciado();
	        	}
	        }
	        logger.info(" ******************** factoresMejora [" + factoresMejora + "] *****************************");
	        logger.info(" ******************** factoresSufis [" + factoresSufis + "] *****************************");
	        logger.info(" ******************** factoresSobresa [" + factoresSobresa + "] *****************************");
	        String competenciasMejora = "";
	        String competenciasSufis = "";
	        String competenciasSobresa = "";
	        for(CuetionarioParticipante cp: competencias) {
	        	int respJefe = cp.getRespuestaJefe()!=null?cp.getRespuestaJefe().intValue():0;
	        	int respRH = cp.getRespuestaRh()!=null?cp.getRespuestaRh().intValue():0;
	        	int promedio = (respJefe + respRH)/2;
	        	if(promedio > 0 && promedio<3) {
	        		competenciasMejora = competenciasMejora + (competenciasMejora.length() != 0?", ":"") + cp.getPregunta().getEnunciado();
	        	} else if(promedio == 3) {
	        		competenciasSufis = competenciasSufis + (competenciasSufis.length() != 0?", ":"") + cp.getPregunta().getEnunciado();
	        	} else if(promedio>3) {
	        		competenciasSobresa = competenciasSobresa + (competenciasSobresa.length() != 0?", ":"") + cp.getPregunta().getEnunciado();
	        	}
	        }
	        logger.info(" ******************** competenciasMejora [" + competenciasMejora + "] *****************************");
	        logger.info(" ******************** competenciasSufis [" + competenciasSufis + "] *****************************");
	        logger.info(" ******************** competenciasSobresa [" + competenciasSobresa + "] *****************************");
	        if(x4 != null){
	        	XWPFTableRow row2 = x4.getRow(2);
	        	row2.getCell(0).setText(factoresMejora);
	        	row2.getCell(1).setText(factoresSufis);
	        	row2.getCell(2).setText(factoresSobresa);
	        }
	        
	        if(x5 != null){
	        	XWPFTableRow row2 = x5.getRow(2);
	        	row2.getCell(0).setText(competenciasMejora);
	        	row2.getCell(1).setText(competenciasSufis);
	        	row2.getCell(2).setText(competenciasSobresa);
	        }
	        
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                
	            	chart = (XWPFChart) part;  
	                String title= chart.getTitle().getBody().getParagraph(0).getText();
	                
	                if(title.equals("Comparativo del promedio de competencias")) {
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
	                
	                if(title.equals("Cumplimiento por competencia")) {
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
	        	        
	        String nombreReporte = "ReporteEVD_" + nombreParticipante + ".docx";
	        
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
	
	protected void dispersionMock(CuetionarioParticipante cp) {
		if(cp.getRespuesta()==null) {
			cp.setRespuesta(0);
		}
		switch(cp.getRespuesta()) {
			case 0: cp.setRespuestaJefe(0);
	    			cp.setRespuestaRh(0);
	    		break;
			case 1: cp.setRespuestaJefe(1);
					cp.setRespuestaRh(1);
	    		break;
			case 2: cp.setRespuestaJefe(1);
					cp.setRespuestaRh(3);
				break;
			case 3: cp.setRespuestaJefe(4);
					cp.setRespuestaRh(2);
				break;
			case 4: cp.setRespuestaJefe(5);
					cp.setRespuestaRh(3);
				break;
			case 5: cp.setRespuestaJefe(5);
					cp.setRespuestaRh(5);
				break;
		}
	}
}
