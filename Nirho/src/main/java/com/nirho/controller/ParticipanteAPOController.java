package com.nirho.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.nirho.dto.NivelDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;
import com.nirho.model.Proyecto;
import com.nirho.service.EstatusProyectoService;
import com.nirho.service.ParticipanteAPOService;
import com.nirho.service.ProyectoService;
import com.nirho.service.UsuarioService;

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
	public List<NivelDTO> organigrama(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		List<NivelDTO> organigrama = new ArrayList<>();
		try {
			organigrama = participanteAPOService.obtenerParticipantesPorProyecto(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los proyectos");
		}
		return organigrama;
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
				ParticipanteAPOAmp participante = assamblerToParticipanteHCA(jsonParticipantesAmp.getJSONObject(i));
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
		participante.setFunciones(jsonParticipanteAmp.optString("funciones", null));
		participante.setActividades(jsonParticipanteAmp.optString("actividades", null));
		participante.setMetaKpi(jsonParticipanteAmp.optString("metaKpi", null));
		participante.setCantidadMeta(jsonParticipanteAmp.optString("cantidadMeta", null));
		participante.setUnidadMedida(jsonParticipanteAmp.optString("unidadMedida", null));
		participante.setTiempo(jsonParticipanteAmp.optString("tiempo", null));
		participante.setFrecuenciaEval(jsonParticipanteAmp.optString("frecuenciaEval", null));
		try {
			participante.setIdParticipante(Integer.parseInt(jsonParticipanteAmp.optString("idParticipante", "0")));
			participante.setIdEvaluador(Integer.parseInt(jsonParticipanteAmp.optString("idEvaluador", "0")));
		} catch(Exception e) {
			logger.info("Exception [" + e.getMessage() + "]");
		}
		return participante;
	}
	
}
