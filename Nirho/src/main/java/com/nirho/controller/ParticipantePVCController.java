package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.servlet.http.HttpServletRequest;

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
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipantePVC;
import com.nirho.model.Proyecto;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.model.Usuario;
import com.nirho.service.EmailService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipantePVCService;
import com.nirho.service.ProyectoPVCAreaService;
import com.nirho.service.ProyectoPVCEspecialidadService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;

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
	public void enviaCorreo(@RequestParam(name="idProyecto") Integer idProyecto, HttpServletRequest request) throws NirhoControllerException{
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
						enviarCorreoParticipantePVC(p, proyecto, token, request);
						
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
					actual.accumulate("nivel", participante.getNivel());
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
									for(ProyectoPVCNivel nivel: esfera.getNiveles()) {
										if(nivel.getNombre().equals(participante.getNivel())) {
											for(ProyectoPVCEspecialidad especialidad: nivel.getEspecialidades()) {
												if(especialidad.getNombre().equals(participante.getEspecialidad())) {
													for(ProyectoPVCConocimiento conocimiento: especialidad.getConocimientos()) {
														if(conocimientoSiguiente) {
															if(conocimiento.getTipo() == 1 ) {
																conocimientosTecnicosSiguientes.put(conocimiento.getNombre());
															}
															if(conocimiento.getTipo() == 2) {
																conocimientosHumanosSiguientes.put(conocimiento.getNombre());
															}
															auxBreak = true;
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
		participante.setSede(jsonParticipante.optString("area", null));
		participante.setSede(jsonParticipante.optString("esfera", null));
		participante.setSede(jsonParticipante.optString("nivel_p", null));
		participante.setSede(jsonParticipante.optString("especialidad", null));
		return participante;
	}
	
	
	private void enviarCorreoParticipantePVC(ParticipantePVC participante, Proyecto proyecto, String token, HttpServletRequest request) {
		try {
    		EmailDatos datos = new EmailDatos();
    		datos.setEmailDestino(participante.getCorreoElectronico());
    		datos.setNombreParticipante(participante.getNombres());
    		datos.setNombreProyecto(proyecto.getNombre());
    		datos.setToken(token);
    		String usuario = (String) request.getAttribute("username");
			Usuario usuarioEnSesion = usuarioService.obtenerUsuario(usuario);
    		emailService.sendEmailPVC(datos, usuarioEnSesion.getEmail());
    	} catch(NirhoServiceException nse) {
    		logger.info("Problemas al enviar un email, causa + [" + nse.getMessage() +"]");
    	}
	}
	
	
}
