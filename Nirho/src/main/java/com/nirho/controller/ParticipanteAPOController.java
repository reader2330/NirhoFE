package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirho.constant.ProyectoConstants;
import com.nirho.dto.EmailDatos;
import com.nirho.dto.ParticipanteDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRH;
import com.nirho.model.Participante;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.model.ParticipanteAPOAmpActividad;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.model.Proyecto;
import com.nirho.model.Usuario;
import com.nirho.service.EmailService;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipanteAPOAmpActividadService;
import com.nirho.service.ParticipanteAPOAmpFuncionService;
import com.nirho.service.ParticipanteAPOService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;
import com.nirho.util.EmailUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/participantesAPO" )
public class ParticipanteAPOController {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOController.class);
	 
	@Autowired
	ParticipanteAPOService participanteAPOService;
	@Autowired
	ParticipanteAPOAmpFuncionService participanteAPOAmpFuncionService;
	@Autowired
	ParticipanteAPOAmpActividadService participanteAPOAmpActividadService;
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	UsuarioService usuarioService;
	
	@Value("${jwt.secret}")
    private String SECRET;
    
	@GetMapping(value = "/organigrama")
	public ParticipanteDTO organigrama(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			
			List<ParticipanteAPO> participantes = participanteAPOService.obtenerParticipantesPorProyecto(idProyecto);
			Queue<ParticipanteDTO> colaParticipantes = new LinkedList();
			
			ParticipanteDTO organigrama = null;
			for (ParticipanteAPO p: participantes) {
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
			
			List<ParticipanteAPO> participantes = participanteAPOService.obtenerParticipantesPorProyecto(idProyecto);
			
			for (ParticipanteAPO p: participantes) {
				if(p.getCorreoElectronico() != null && !p.getCorreoElectronico().isEmpty()) {
					Proyecto proyecto = proyectoService.obtenerProyectoPorId(p.getIdProyecto());
					if(proyecto != null) {	
						
						String token = Jwts.builder()
				                .claim("jefe", false)
				                .claim("id", p.getIdParticipante())
				                .signWith( SignatureAlgorithm.HS512, SECRET )
				                .compact();
						enviarCorreoParticipanteAPO(p, proyecto, token, request);
						
						if(p.getIdPartJefeInm() != 0) {
							ParticipanteAPO jefe = participanteAPOService.getOne(p.getIdPartJefeInm());
							String tokenJefe = Jwts.builder()
					                .claim("jefe", true)
					                .claim("id", jefe.getIdParticipante())
					                .signWith( SignatureAlgorithm.HS512, SECRET )
					                .compact();
							enviarCorreoParticipanteAPO(jefe, proyecto, tokenJefe, request);
						}
						
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
	        	
	        	
	        	
	        	JSONObject response = new JSONObject();
	        	try {
	        		ObjectMapper o = new ObjectMapper();
		        	String userJsonString = o.writeValueAsString(participanteAPOService.getOne(idParticipante));
					response.put("participante", new JSONObject(userJsonString));
					response.put("jefe", (boolean)claims.get("jefe"));
					return response.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
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
	public List<ParticipanteAPO> participantes(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			return participanteAPOService.obtenerParticipantes(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el head count de la base de datos");
		}
	}
	
	@GetMapping(value = "/ampliaciones")
	public Set<ParticipanteAPOAmp> ampliaciones(@RequestParam(name="idParticipante") Integer idParticipante) throws NirhoControllerException{
		try {
			return participanteAPOService.obtenerAmpliaciones(idParticipante);
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
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
			proyecto.setIdEstatus(estatusService.obtenerEstatus(ProyectoConstants.ESTATUS_CARGA));
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			
			JSONArray jsonParticipantes = jsonHeadCount.getJSONArray("participantes");
			List<ParticipanteAPO> participantes = new ArrayList<>();
			for(int i = 0; i < jsonParticipantes.length(); i++) {
				try {
					ParticipanteAPO participante = assamblerToParticipanteHC(jsonParticipantes.getJSONObject(i));
					participante.setIdProyecto(proyecto.getIdProyecto());
					participantes.add(participante);
				}catch(Exception e) {
					logger.info("Exception [" + e.getMessage() + "]");
				}
			}
			participanteAPOService.guardarParticipanteService(participantes);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		}
	}

	@RequestMapping(value = "/headCountAmp", method = RequestMethod.POST)
	@ResponseBody
	public void headCountAmp(@RequestBody String headcount) throws NirhoControllerException {
		
		logger.info(" ************************ headcount [" + headcount + "] *****************************");
		
		try {
			
			JSONObject jsonHeadCount = new JSONObject(headcount);
			JSONArray jsonParticipantesAmp = jsonHeadCount.getJSONArray("participantes");
			List<ParticipanteAPOAmp> participantesAmp = new ArrayList<>();
			for(int i = 0; i < jsonParticipantesAmp.length(); i++) {	
				
				ParticipanteAPOAmp participante = null;
				boolean addParticiante = true;
				
				for(ParticipanteAPOAmp pa: participantesAmp) {
					if(pa.getIdParticipante() == Integer.parseInt(jsonParticipantesAmp.getJSONObject(i).optString("idParticipante", "0"))) {

						ParticipanteAPOAmpFuncion funcion = new ParticipanteAPOAmpFuncion();
						funcion.setFuncion(jsonParticipantesAmp.getJSONObject(i).optString("funciones", null));
						funcion.setMetaKpi(jsonParticipantesAmp.getJSONObject(i).optString("metaKpi", null));
						funcion.setCantidadMeta(jsonParticipantesAmp.getJSONObject(i).optString("cantidadMeta", null));
						funcion.setTiempo(jsonParticipantesAmp.getJSONObject(i).optString("tiempo", null));
						funcion.setFrecuenciaEval(jsonParticipantesAmp.getJSONObject(i).optString("frecuenciaEval", null));
						try {
							funcion.setIdEvaluador(Integer.parseInt(jsonParticipantesAmp.getJSONObject(i).optString("idEvaluador", "0")));
						} catch(Exception e) {
							logger.info("Exception [" + e.getMessage() + "]");
						}
						pa.getFunciones().add(funcion);
						addParticiante = false;
						break;
						
					}
				}
				
				if(!addParticiante) {
					continue;
				}
				
				if(participante == null) {
					participante = assamblerToParticipanteHCA(jsonParticipantesAmp.getJSONObject(i));
					List<ParticipanteAPOAmpFuncion> funciones = new ArrayList<>();
					
					ParticipanteAPOAmpFuncion funcion = new ParticipanteAPOAmpFuncion();
					funcion.setFuncion(jsonParticipantesAmp.getJSONObject(i).optString("funciones", null));
					funcion.setMetaKpi(jsonParticipantesAmp.getJSONObject(i).optString("metaKpi", null));
					funcion.setCantidadMeta(jsonParticipantesAmp.getJSONObject(i).optString("cantidadMeta", null));
					funcion.setTiempo(jsonParticipantesAmp.getJSONObject(i).optString("tiempo", null));
					funcion.setFrecuenciaEval(jsonParticipantesAmp.getJSONObject(i).optString("frecuenciaEval", null));
					try {
						funcion.setIdEvaluador(Integer.parseInt(jsonParticipantesAmp.getJSONObject(i).optString("idEvaluador", "0")));
					} catch(Exception e) {
						logger.info("Exception [" + e.getMessage() + "]");
					}
					funciones.add(funcion);
					
					participante.setFunciones(funciones);
					
				}

				participantesAmp.add(participante);
				
			} 
			participanteAPOService.ampliarParticipanteService(participantesAmp);
		}  catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al registrar el participante en la BD");
		}
	}

	@RequestMapping(value = "/funciones/todas", method = RequestMethod.GET)
	public List<ParticipanteAPOAmpFuncion> listFunciones() throws NirhoControllerException{
		try {
			return participanteAPOAmpFuncionService.list();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/funciones/{id}", method = RequestMethod.GET)
	public ParticipanteAPOAmpFuncion getFuncion(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return participanteAPOAmpFuncionService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/funciones/guardar", method = RequestMethod.POST)
	public void saveFuncion(@Valid @RequestBody ParticipanteAPOAmpFuncion f) throws NirhoControllerException{
		try {
			participanteAPOAmpFuncionService.guardar(f);	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/funciones/todas/guardar", method = RequestMethod.POST)
	public void editFuncion(@Valid @RequestBody List<ParticipanteAPOAmpFuncion> l) throws NirhoControllerException{
		try {
			participanteAPOAmpFuncionService.guardar(l);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/funciones/{idFuncion}/actividades/todas", method = RequestMethod.GET)
	public List<ParticipanteAPOAmpActividad> listActividades(@PathVariable("idFuncion") int idFuncion) throws NirhoControllerException{
		try {
			ParticipanteAPOAmpFuncion funcion = participanteAPOAmpFuncionService.getOne(idFuncion);
			if(funcion != null) {
				return funcion.getActividades();
			} else {
				return null;
			}
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/funciones/actividades/{id}", method = RequestMethod.GET)
	public ParticipanteAPOAmpActividad getActividad(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			return participanteAPOAmpActividadService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/funciones/{idFuncion}/actividades/guardar", method = RequestMethod.POST)
	public void saveActividad(@PathVariable("idFuncion") int idFuncion,@Valid @RequestBody ParticipanteAPOAmpActividad a) throws NirhoControllerException{
		try {
			ParticipanteAPOAmpFuncion funcion = participanteAPOAmpFuncionService.getOne(idFuncion);
			if(funcion != null) {
				if(funcion.getActividades() == null) {
					List<ParticipanteAPOAmpActividad> l = new ArrayList<>();
					l.add(a);
					funcion.setActividades(l);
				} else {
					funcion.getActividades().add(a);
				}
				participanteAPOAmpFuncionService.guardar(funcion);
			}	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/funciones/{idFuncion}/actividades/todas/guardar", method = RequestMethod.POST)
	public void editActividad(@PathVariable("idFuncion") int idFuncion, @Valid @RequestBody List<ParticipanteAPOAmpActividad> l) throws NirhoControllerException{
		try {
			ParticipanteAPOAmpFuncion funcion = participanteAPOAmpFuncionService.getOne(idFuncion);
			if(funcion != null) {
				if(funcion.getActividades() == null) {
					funcion.setActividades(l);
				} else {
					funcion.getActividades().addAll(l);
				}
			}	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/funciones/{idFuncion}/actividades/{idActividad}/calificar/{calificacion}", method = RequestMethod.POST)
	public void editActividad(@PathVariable("idFuncion") int idFuncion, @PathVariable("idActividad") int idActividad, @PathVariable("calificacion") int calificacion) throws NirhoControllerException{
		try {
			ParticipanteAPOAmpActividad a = participanteAPOAmpActividadService.getOne(idActividad);
			ParticipanteAPOAmpFuncion f = participanteAPOAmpFuncionService.getOne(idFuncion);
			if(a != null && f != null) {
				a.setCalificacion(calificacion);
				participanteAPOAmpActividadService.guardar(a);
				int calificacionFuncion = 0;
				for(ParticipanteAPOAmpActividad actividad: f.getActividades()) {
					calificacionFuncion += actividad.getCalificacion() == null ? 0 : actividad.getCalificacion();
				}
				f.setCalificacion(Math.round(calificacionFuncion/f.getActividades().size()));
				participanteAPOAmpFuncionService.guardar(f);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "{idParticipante}/funciones/calificaciones", method = RequestMethod.POST)
	public void funcionCalificaciones(@PathVariable("idParticipante") int idParticipante, @Valid @RequestBody List<ParticipanteAPOAmpActividad> l) throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
		    JSONArray funciones = new JSONArray(); 
		    int sumaCalificaciones = 0;
		    int numFunciones = 0;
			ParticipanteAPO participante = participanteAPOService.getOne(idParticipante);
			for(ParticipanteAPOAmp ampliacion: participante.getAmpliaciones()) {
				for(ParticipanteAPOAmpFuncion funcion: ampliacion.getFunciones()) {
					JSONObject jsonFuncion = new JSONObject();
					jsonFuncion.accumulate("id", funcion.getId());
					jsonFuncion.accumulate("funcion", funcion.getFuncion());
					jsonFuncion.accumulate("calificacion", funcion.getCalificacion());
					funciones.put(jsonFuncion);
					numFunciones++;
					sumaCalificaciones += funcion.getCalificacion() == null ? 0 : funcion.getCalificacion();
				}
			}
			response.put("funciones", funciones);
			response.put("promedio", Math.round(sumaCalificaciones / numFunciones) );
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	private ParticipanteAPO assamblerToParticipanteHC(JSONObject jsonParticipante) throws JSONException {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		ParticipanteAPO participante = new ParticipanteAPO();
		participante.setIdParticipante(Integer.parseInt(jsonParticipante.optString("idParticipante", "0")));
		participante.setNivel(Integer.parseInt(jsonParticipante.optString("nivel", "0")));
		participante.setNivelTexto(jsonParticipante.optString("nivelTexto", null));
		participante.setNombres(jsonParticipante.optString("nombres", null));
	    participante.setAPaterno(jsonParticipante.optString("aPaterno", null));
		participante.setAMaterno(jsonParticipante.optString("aMaterno", null));
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
		return participante;
	}

	private ParticipanteAPOAmp assamblerToParticipanteHCA(JSONObject jsonParticipanteAmp) throws JSONException {
		ParticipanteAPOAmp participante = new ParticipanteAPOAmp();
		participante.setObjetivoPuesto(jsonParticipanteAmp.optString("objetivoPuesto", null));
		try {
			participante.setIdParticipante(Integer.parseInt(jsonParticipanteAmp.optString("idParticipante", "0")));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "]");
		}
		return participante;
	}
	
	private void enviarCorreoParticipanteAPO(ParticipanteAPO participante, Proyecto proyecto, String token, HttpServletRequest request) {
		try {
    		EmailDatos datos = new EmailDatos();
    		datos.setEmailDestino(participante.getCorreoElectronico());
    		datos.setNombreParticipante(participante.getNombres());
    		datos.setNombreProyecto(proyecto.getNombre());
    		datos.setToken(token);
    		String usuario = (String) request.getAttribute("username");
			Usuario usuarioEnSesion = usuarioService.obtenerUsuario(usuario);
    		emailService.sendEmailAPO(datos, usuarioEnSesion.getEmail());
    	} catch(NirhoServiceException nse) {
    		logger.info("Problemas al enviar un email, causa + [" + nse.getMessage() +"]");
    	}
	}
	
}
