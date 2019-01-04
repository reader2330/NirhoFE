package com.nirho.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.jboss.logging.Logger;
import org.json.JSONArray; 
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.EmailDatos;
import com.nirho.dto.ParticipanteDTO;
import com.nirho.dto.ProyectoPVCNivelDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipantePVC;
import com.nirho.model.Proyecto;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.service.EmailService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipantePVCService;
import com.nirho.service.ProyectoPVCAreaService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;
import com.nirho.util.ReporteUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/participantesPVC" )
public class ParticipantePVCController {
	
	public final static Logger logger = Logger.getLogger(ParticipantePVCController.class);
	 
	@Autowired
	ParticipantePVCService participantePVCService;
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	private ProyectoPVCAreaService proyectoPVCAreaService;
	@Autowired
	EmailService emailService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	UsuarioService usuarioService;
	
	@Value("${jwt.secret}")
    private String SECRET;
    
	@GetMapping(value = "/organigrama")
	public ParticipanteDTO organigrama(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			
			List<ParticipantePVC> participantes = participantePVCService.getByProyecto(idProyecto);
			Queue<ParticipanteDTO> colaParticipantes = new LinkedList();
			
			ParticipanteDTO organigrama = null;
			for (ParticipantePVC p: participantes) {
				if(p.getIdPartJefeInm() == 0) {
					organigrama = new ParticipanteDTO(p.getIdParticipante(), p.getNombres(), p.getPuesto(), p.getIdPartJefeInm());
				} else {
					colaParticipantes.add(new ParticipanteDTO(p.getIdParticipante(), p.getNombres(), p.getPuesto(), p.getIdPartJefeInm()));
				}
			}
			
			int i = 0;
			int s = colaParticipantes.size();
			
			while(colaParticipantes.peek() != null && i < s*10) {
				ParticipanteDTO p = colaParticipantes.poll();
				ParticipanteDTO organigramaAux = insertToOrganigrama(organigrama, p);
				if(organigramaAux == null) {
					colaParticipantes.add(p);
				}
				s++;
			}
			
			return organigrama;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
	}
	
	@GetMapping(value = "/enviocorreo")
	public void enviaCorreo(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			
			List<ParticipantePVC> participantes = participantePVCService.getByProyecto(idProyecto);
			
			for (ParticipantePVC p: participantes) {
				if(p.getCorreoElectronico() != null && !p.getCorreoElectronico().isEmpty()) {
					Proyecto proyecto = proyectoService.obtenerProyectoPorId(p.getIdProyecto());
					if(proyecto != null) {	
						
						String token = Jwts.builder()
				                .claim("id", p.getIdParticipante())
				                .claim("idProyecto", idProyecto)
				                .signWith( SignatureAlgorithm.HS512, SECRET )
				                .compact();
						enviarCorreoParticipantePVC(p, proyecto, token);
						
					}
				} 
			}
			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
	}
	
	@GetMapping(value = "/{token}")
	public String porToken(@PathVariable("token") String token) throws NirhoControllerException{
		try {
			
			Claims claims;
	        try {
	            claims = Jwts.parser()
	                    .setSigningKey(this.SECRET)
	                    .parseClaimsJws(token)
	                    .getBody();
	        } catch (Exception e) {
	            claims = null;
	        }
	        
	        if(claims != null) {
	        	int idParticipante = (int)claims.get("id");
	        	int idProyecto = (int)claims.get("idProyecto");
	        	
	        	JSONObject response = new JSONObject();
	        	try {
		        	ParticipantePVC participante = participantePVCService.getOne(idParticipante);
					JSONObject actual = new JSONObject();
					actual.accumulate("area", participante.getArea());
					actual.accumulate("esfera", participante.getEsfera());
					actual.accumulate("nivel", participante.getNivelP());
					actual.accumulate("especilidad", participante.getEspecialidad());
					
					JSONArray conocimientosTecnicos = new JSONArray();
					JSONArray conocimientosHumanos = new JSONArray();
					
					JSONArray conocimientosTecnicosSiguientes = new JSONArray();
					JSONArray conocimientosHumanosSiguientes = new JSONArray();
					
					boolean auxBreak = false;
					boolean conocimientoSiguiente = false;
					
					List<ProyectoPVCArea> areas = proyectoPVCAreaService.getByProyecto(idProyecto);
					for(ProyectoPVCArea area: areas) {
						if(area.getNombre().equals(participante.getArea())) {
							for(ProyectoPVCEsfera esfera: area.getEsferas()) {
								if(esfera.getNombre().equals(participante.getEsfera())) {
									List<ProyectoPVCNivelDTO> nivelesDTO = new ArrayList<>();
									for(ProyectoPVCNivel nivel: esfera.getNiveles()) {
										nivelesDTO.add(new ProyectoPVCNivelDTO(nivel.getId(), nivel.getNombre(), nivel.isStatus(), nivel.getEspecialidades()));
									}
									Collections.sort(nivelesDTO);
									

									for(ProyectoPVCNivelDTO nivel: nivelesDTO) {
										
										if(nivel.getNombre().equals(participante.getNivelP()) || conocimientoSiguiente) {
											
											for(ProyectoPVCEspecialidad especialidad: nivel.getEspecialidades()) {
												if(especialidad.getNombre().equals(participante.getEspecialidad())) {
													
													if(conocimientoSiguiente) {
														actual.accumulate("nivelSiguiente", nivel.getNombre());
														actual.accumulate("esferaSiguiente", esfera.getNombre());
														auxBreak = true;
													}
													
													for(ProyectoPVCConocimiento conocimiento: especialidad.getConocimientos()) {
														if(conocimientoSiguiente) {
															if(conocimiento.getTipo() == 1 ) {
																JSONObject c = new JSONObject();
																c.accumulate("nombre", conocimiento.getNombre());
																c.accumulate("calificacion", conocimiento.getCalificacion());
																c.accumulate("id", conocimiento.getId());
																conocimientosTecnicosSiguientes.put(c);
															}
															if(conocimiento.getTipo() == 2) {
																JSONObject c = new JSONObject();
																c.accumulate("nombre", conocimiento.getNombre());
																c.accumulate("calificacion", conocimiento.getCalificacion());
																c.accumulate("id", conocimiento.getId());
																conocimientosHumanosSiguientes.put(c);
															}
														} else {
															if(conocimiento.getTipo() == 1 ) {
																JSONObject c = new JSONObject();
																c.accumulate("nombre", conocimiento.getNombre());
																c.accumulate("calificacion", conocimiento.getCalificacion());
																c.accumulate("id", conocimiento.getId());
																conocimientosTecnicos.put(c);
															}
															if(conocimiento.getTipo() == 2) {
																JSONObject c = new JSONObject();
																c.accumulate("nombre", conocimiento.getNombre());
																c.accumulate("calificacion", conocimiento.getCalificacion());
																c.accumulate("id", conocimiento.getId());
																conocimientosHumanos.put(c);
															}
														}
													}
													
													conocimientoSiguiente = true;
													break;
												}
												
												if(auxBreak) break;
											}
																				
											
										}
										if(auxBreak) break;
									}
									

								}
								if(auxBreak) break;
							}
						}
						if(auxBreak) break;
					}
					
					actual.put("conocimientosTecnicos", conocimientosTecnicos);
					actual.put("conocimientosHumanos", conocimientosHumanos);
					actual.put("conocimientosTecnicosSiguientes", conocimientosTecnicosSiguientes);
					actual.put("conocimientosHumanosSiguientes", conocimientosHumanosSiguientes);
					
					
					return actual.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
	        }
	        
	        return null;
	        
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		
	}
	
	private ParticipanteDTO insertToOrganigrama(ParticipanteDTO organigrama, ParticipanteDTO participante) {
		if(organigrama == null) {
			return null;
		}
		if(participante.getIdJefeInmediato() == organigrama.getIdParticipante()) {
			if(organigrama.getSubordinados() == null) {
				List<ParticipanteDTO> subordinados = new ArrayList<>();
				subordinados.add(participante);
				organigrama.setSubordinados(subordinados);
				return organigrama;
			} else {
				organigrama.getSubordinados().add(participante);
				return organigrama;
			}
		} else {
			if(organigrama.getSubordinados() == null) {
				return null;
			} else {
				for(ParticipanteDTO p: organigrama.getSubordinados()) {
					ParticipanteDTO pp = insertToOrganigrama(p, participante);
					if(pp != null) {
						return pp;
					}
				}
				return null;
			}
		}
	}

	@GetMapping(value = "/participantes")
	public List<ParticipantePVC> participantes(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			return participantePVCService.getAll(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el head count de la base de datos");
		}
	}
	
	
	@RequestMapping(value = "/headCount", method = RequestMethod.POST)
	@ResponseBody
	public void guardarParticipantes(@RequestBody String headcount) throws NirhoControllerException {
		logger.info(" ************************ headcount [" + headcount + "] *****************************");
		try {
			JSONObject jsonHeadCount = new JSONObject(headcount);
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(jsonHeadCount.getInt("idProyecto"));
			proyecto.setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_CARGA));
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			
			JSONArray jsonParticipantes = jsonHeadCount.getJSONArray("participantes");
			List<ParticipantePVC> participantes = new ArrayList<>();
			for(int i = 0; i < jsonParticipantes.length(); i++) {
				try {
					ParticipantePVC participante = assamblerToParticipanteHC(jsonParticipantes.getJSONObject(i));
					participante.setIdParticipante( Integer.valueOf((participante.getIdParticipante() + "") + (proyecto.getIdProyecto() + "")) );
					participante.setIdProyecto(proyecto.getIdProyecto());
					participantes.add(participante);
				}catch(Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
			participantePVCService.save(participantes);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		}
	}

	private ParticipantePVC assamblerToParticipanteHC(JSONObject jsonParticipante) throws JSONException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		ParticipantePVC participante = new ParticipantePVC();
		participante.setIdParticipante(Integer.parseInt(jsonParticipante.optString("idParticipante", "0")));
		participante.setNivel(Integer.parseInt(jsonParticipante.optString("nivel", "0")));
		participante.setNivelTexto(jsonParticipante.optString("nivelTexto", null));
		participante.setNombres(jsonParticipante.optString("nombres", null));
	    participante.setaPaterno(jsonParticipante.optString("aPaterno", null));
		participante.setaMaterno(jsonParticipante.optString("aMaterno", null));
		participante.setGenero(jsonParticipante.optString("genero", null));
		participante.setRfc(jsonParticipante.optString("rfc", null));
		participante.setPuesto(jsonParticipante.optString("puesto", null));
		try {
			participante.setFechaIngreso(formatDate.parse(jsonParticipante.optString("fechaIngreso", "")));
		} catch (ParseException e) {
			participante.setFechaIngreso(new Date());
		}
		try { 
			participante.setAntigPuesto(new Double(jsonParticipante.optString("antigPuesto", "0.0")));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "]");
		}
		participante.setNivelEscolaridad(jsonParticipante.optString("nivelEscolaridad", null));
		participante.setOtrosEstudios(jsonParticipante.optString("otrosEstudios", null));
		participante.setIdioma(jsonParticipante.optString("idioma", null));
		participante.setNivelIdioma(jsonParticipante.optString("nivelIdioma", null));
		participante.setCorreoElectronico(jsonParticipante.optString("correoElectronico", null));
		participante.setSede(jsonParticipante.optString("sede", null));
		participante.setAreaOrg(jsonParticipante.optString("areaOrg", null));
		participante.setIdPartJefeInm(Integer.parseInt(jsonParticipante.optString("idPartJefeInm", "0")));
		participante.setArea(jsonParticipante.optString("area", null));
		participante.setEsfera(jsonParticipante.optString("esfera", null));
		participante.setNivelP(jsonParticipante.optString("nivel_p", null));
		participante.setEspecialidad(jsonParticipante.optString("especialidad", null));
		return participante;
	}
	
	
	private void enviarCorreoParticipantePVC(ParticipantePVC participante, Proyecto proyecto, String token) {
		try {
    		EmailDatos datos = new EmailDatos();
    		datos.setEmailDestino(participante.getCorreoElectronico());
    		datos.setNombreParticipante(participante.getNombres());
    		datos.setNombreProyecto(proyecto.getNombre());
    		datos.setToken(token);
    		emailService.sendEmailPVC(datos);
    	} catch(NirhoServiceException nse) {
    		logger.info("Problemas al enviar un email, causa + [" + nse.getMessage() +"]");
    	}
	}
	
	
	@RequestMapping(value = "/reporte/participante", method = RequestMethod.GET)
	@ResponseBody
	public void genearReporteIndividual(@RequestParam(name="idParticipante") Integer idParticipante, HttpServletResponse response) throws NirhoControllerException{
		
		try {
			    
			ZipSecureFile.setMinInflateRatio(0);
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss/jboss-eap-7.1/standalone/deployments/reportePVC.docx"));
			XWPFDocument document = new XWPFDocument(OPCPackage.open("/opt/jboss-eap-7.1/standalone/deployments/reportePVC.docx"));
			//XWPFDocument document = new XWPFDocument(OPCPackage.open("C:\\Users\\Alfredo\\elimina\\reportePVC.docx"));

	        ParticipantePVC participante = participantePVCService.getOne(idParticipante);

	        XWPFTable informacionGeneral =  ReporteUtil.getTablaPorTitulo(document, "Información general");
	        if(informacionGeneral != null){
	            XWPFTableRow row0 = informacionGeneral.getRow(0);
	            row0.getCell(1).setText(participante.getNombres() + " " + participante.getaPaterno() + " " + participante.getaMaterno());
	            row0.getCell(3).setText(new SimpleDateFormat("yy-MM-dd").format(participante.getFechaIngreso()));
	            XWPFTableRow row1 = informacionGeneral.getRow(1);
	            row1.getCell(1).setText(participante.getPuesto());
	            row1.getCell(3).setText(participante.getAntigPuesto() + "");
	        }

	        XWPFTable semaforo =  ReporteUtil.getTablaPorTitulo(document, "semaforo");
	        
	        if(semaforo != null){
	          
	            XWPFParagraph p1 = semaforo.getRow(0).getCell(0).getParagraphs().get(0);
	            XWPFRun r1 = p1.createRun();
	            if(participante.getAntigPuesto() >= 0 && participante.getAntigPuesto() <= 2) {
	            	r1.setBold(true);
		            r1.setUnderline(UnderlinePatterns.SINGLE);	
	            }
	            r1.setText("0 - 2 años", 0);
	            
	            XWPFParagraph p2 = semaforo.getRow(1).getCell(0).getParagraphs().get(0);
	            XWPFRun r2 = p2.createRun();
	            if(participante.getAntigPuesto() >= 2 && participante.getAntigPuesto() <= 4) {
	            	r2.setBold(true);
		            r2.setUnderline(UnderlinePatterns.SINGLE);	
	            }
	            r2.setText("2 años 1 mes " +" – " +" 4 años", 0);
	            
	            XWPFParagraph p3 = semaforo.getRow(2).getCell(0).getParagraphs().get(0);
	            XWPFRun r3 = p3.createRun();
	            if(participante.getAntigPuesto() >= 4) {
	            	r3.setBold(true);
		            r3.setUnderline(UnderlinePatterns.SINGLE);	
	            }
	            r3.setText("4 años 1 mes", 0);
	            
	        }
	        
	        XWPFTable conocimientos =  ReporteUtil.getTablaPorTitulo(document, "conocimientos");
	        
	        if(conocimientos != null){
	        
	        	JSONArray conocimientosTecnicos = new JSONArray();
				JSONArray conocimientosHumanos = new JSONArray();
				
				JSONArray conocimientosTecnicosSiguientes = new JSONArray();
				JSONArray conocimientosHumanosSiguientes = new JSONArray();
				
				boolean auxBreak = false;
				boolean conocimientoSiguiente = false;
				
				List<ProyectoPVCArea> areas = proyectoPVCAreaService.getByProyecto(participante.getIdProyecto());
				for(ProyectoPVCArea area: areas) {
					if(area.getNombre().equals(participante.getArea())) {
						for(ProyectoPVCEsfera esfera: area.getEsferas()) {
							if(esfera.getNombre().equals(participante.getEsfera())) {
								List<ProyectoPVCNivelDTO> nivelesDTO = new ArrayList<>();
								for(ProyectoPVCNivel nivel: esfera.getNiveles()) {
									nivelesDTO.add(new ProyectoPVCNivelDTO(nivel.getId(), nivel.getNombre(), nivel.isStatus(), nivel.getEspecialidades()));
								}
								Collections.sort(nivelesDTO);
								
								for(ProyectoPVCNivelDTO nivel: nivelesDTO) {
									
									if(nivel.getNombre().equals(participante.getNivelP()) || conocimientoSiguiente) {
										
										for(ProyectoPVCEspecialidad especialidad: nivel.getEspecialidades()) {
											if(especialidad.getNombre().equals(participante.getEspecialidad())) {
												
												if(conocimientoSiguiente) {
													auxBreak = true;
												}
												
												for(ProyectoPVCConocimiento conocimiento: especialidad.getConocimientos()) {
													if(conocimientoSiguiente) {
														if(conocimiento.getTipo() == 1 ) {
															conocimientosTecnicosSiguientes.put(conocimiento.getNombre());
														}
														if(conocimiento.getTipo() == 2) {
															conocimientosHumanosSiguientes.put(conocimiento.getNombre());
														}
													} else {
														if(conocimiento.getTipo() == 1 ) {
															conocimientosTecnicos.put(conocimiento.getNombre());
														}
														if(conocimiento.getTipo() == 2) {
															conocimientosHumanos.put(conocimiento.getNombre());
														}
													}
												}
												
												conocimientoSiguiente = true;
												break;
											}
											
											if(auxBreak) break;
										}
																			
										
									}
									if(auxBreak) break;
								}
								

							}
							if(auxBreak) break;
						}
					}
					if(auxBreak) break;
				}
				
				for(int i = 0; i < conocimientosTecnicos.length(); i++) {
					XWPFParagraph parrafo = conocimientos.getRow(1).getCell(0).addParagraph();
	                parrafo.setStyle("ParrafoNirho");
	                XWPFRun lnewRun = parrafo.createRun();
	                try {
						lnewRun.setText("- " + conocimientosTecnicos.getString(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				for(int i = 0; i < conocimientosHumanos.length(); i++) {
					XWPFParagraph parrafo = conocimientos.getRow(1).getCell(0).addParagraph();
	                parrafo.setStyle("ParrafoNirho");
	                XWPFRun lnewRun = parrafo.createRun();
	                try {
						lnewRun.setText("- " + conocimientosHumanos.getString(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				for(int i = 0; i < conocimientosTecnicosSiguientes.length(); i++) {
					XWPFParagraph parrafo = conocimientos.getRow(3).getCell(0).addParagraph();
	                parrafo.setStyle("ParrafoNirho");
	                XWPFRun lnewRun = parrafo.createRun();
	                try {
						lnewRun.setText("- " + conocimientosTecnicosSiguientes.getString(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				for(int i = 0; i < conocimientosHumanosSiguientes.length(); i++) {
					XWPFParagraph parrafo = conocimientos.getRow(3).getCell(0).addParagraph();
	                parrafo.setStyle("ParrafoNirho");
	                XWPFRun lnewRun = parrafo.createRun();
	                try {
						lnewRun.setText("- " + conocimientosHumanosSiguientes.getString(i));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
	        	
	        }
	        
	        
	        XWPFTable esferas =  ReporteUtil.getTablaPorTitulo(document, "esferas");
	        
	        if(esferas != null){
	           
	        	List<ProyectoPVCArea> areas = proyectoPVCAreaService.getByProyecto(participante.getIdProyecto());
				for(ProyectoPVCArea area: areas) {
					if(area.getNombre().equals(participante.getArea())) {
						for(ProyectoPVCEsfera esfera: area.getEsferas()) {
							XWPFParagraph parrafo = esferas.getRow(0).getCell(0).addParagraph();
			                parrafo.setStyle("ParrafoNirho");
			                XWPFRun lnewRun = parrafo.createRun();
			                if(esfera.getNombre().equals(participante.getEsfera())) {
			                	lnewRun.setBold(true);
			                	lnewRun.setUnderline(UnderlinePatterns.SINGLE);	
				            }
			                lnewRun.setText("- " + esfera.getNombre());
						}
					}
				}
					 
			}
	        	
	        HashMap<String, Integer> datosGrafico = new HashMap<>();
	        
	        List<ProyectoPVCArea> areas = proyectoPVCAreaService.getByProyecto(participante.getIdProyecto());
			for(ProyectoPVCArea area: areas) {
				if(area.getNombre().equals(participante.getArea())) {
					for(ProyectoPVCEsfera esfera: area.getEsferas()) {
						int participantes = 0;
						for(ParticipantePVC p : participantePVCService.getAll(participante.getIdProyecto())) {
							if(esfera.getNombre().equals(p.getEsfera())) {
								participantes++;
							}
						}
						datosGrafico.put(esfera.getNombre(), participantes);
					}
				}
			}
	        
	        XWPFTable resumen =  ReporteUtil.getTablaPorTitulo(document, "resumen");

	        double max = 0.0, min = 0.0;
	        String maxEsfera = "", minEsfera = "";
	        double promedioGeneral = 0;
	        int numPuestos = 0;
	        
	        boolean first = true;
	        for(String key : datosGrafico.keySet()) {
	        	int value = (Integer)datosGrafico.get(key);
	        	if(first) {
	        		max = value;
	        		min = value;
	        		maxEsfera = key;
	        		minEsfera = key;
	        		first = false;
	        	}
	        	if(value > max) {
	        		max = value;
	        		maxEsfera = key;
	        	}
	        	if(value < min) {
	        		min = value;
	        		minEsfera = key;
	        	}
	        	promedioGeneral += value; 
	        	numPuestos++;
	        }
	       
	        promedioGeneral = promedioGeneral / numPuestos;
	        promedioGeneral = Math.round(promedioGeneral * 100.0) / 100.0;
	        
	        if(resumen != null){
	        	XWPFTableRow row1 = resumen.getRow(0);
	        	row1.getCell(1).setText(promedioGeneral + "");
	        	XWPFTableRow row2 = resumen.getRow(1);
	        	row2.getCell(1).setText("Esfera: " + maxEsfera + "\n, Personas en el puesto: " + max);
	        	XWPFTableRow row3 = resumen.getRow(2);
	        	row3.getCell(1).setText("Esfera: " + minEsfera + "\n, Personas en el puesto: " + min);
	        }
	           
	        XWPFChart chart = null;
	        for (POIXMLDocumentPart part : document.getRelations()) {
	            if (part instanceof XWPFChart) {
	                
	            	chart = (XWPFChart) part;  
	                String title= chart.getTitle().getBody().getParagraph(0).getText();
	                
	                if(title.equals("Puestos por esfera")) {
	                	 XSSFWorkbook wb2 = chart.getWorkbook();
		 	             Sheet dataSheet2 = wb2.getSheetAt(0);
		 	             int i = 1;
		 	             for(String key: datosGrafico.keySet()) {
		 	            	Row row = dataSheet2.createRow(i);
	    	            	row.createCell(0).setCellValue(key);
	    	            	row.createCell(1).setCellValue(datosGrafico.get(key));
		 	            	row.createCell(2).setCellValue(promedioGeneral);
		 	            	i++;
		 	             }
	                }
	            }
	        }
	        
	        String nombreReporte = "ReportePVC_" + participante.getNombres() + " " + participante.getaPaterno() + " " + participante.getaMaterno() + ".docx";
	        
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
