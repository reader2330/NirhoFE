package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.nirho.dto.ParticipanteDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.model.Proyecto;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipanteAPOService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;
import com.nirho.util.EmailUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/participantesAPO" )
public class ParticipanteAPOController {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOController.class);
	
	@Autowired
	ParticipanteAPOService participanteAPOService;
	@Autowired
	ProyectoService proyectoService;
	@Autowired
	private EstatusProyectoService estatusService;
	@Autowired
	UsuarioService usuarioService;

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
	public void enviaCorreo(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		try {
			
			List<ParticipanteAPO> participantes = participanteAPOService.obtenerParticipantesPorProyecto(idProyecto);
			
			for (ParticipanteAPO p: participantes) {
				if(p.getIdPartJefeInm() == 0) {
					
				} 
			}
			
			
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
	
}
