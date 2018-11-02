package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.CuestionarioEmpresaDTO;
import com.nirho.dto.PreguntaRespuestaCEmpresaIRHDTO;
import com.nirho.dto.transformer.RespuestaCEmpresaIRHDTOTransoformer;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.PreguntaCuestionarioEmpresa;
import com.nirho.model.PreguntaTema;
import com.nirho.model.RespuestaPreguntaIRH;
import com.nirho.model.TemaCuestionario;
import com.nirho.model.view.VwCuestionarioPreguntasTemas;
import com.nirho.model.view.VwTemaCuestionario;
import com.nirho.service.CuestionarioTemaEmpresaService;

@RestController
@RequestMapping( value = "/cuestionarioEmpresa" )
public class CuestionarioEmpresaController {
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaController.class);
	
	@Autowired
	CuestionarioTemaEmpresaService cuestionarioTemaEmpresaService;

	@RequestMapping(value = "/consultarCuestionariosActivosEmpresa", method = RequestMethod.GET)
	@ResponseBody
	public List<CuestionarioEmpresa> consultarCuestionariosActivosEmpresa(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		List<CuestionarioEmpresa> cuestionariosTemasEmpresa = new ArrayList<CuestionarioEmpresa>();
		try {
			cuestionariosTemasEmpresa = cuestionarioTemaEmpresaService.consultarCuestionariosActivosEmpresa(rfc);
		}catch(NirhoServiceException e){
			logger.error("Problemas al obtener los cuestionarios de la empresa" + e);
			throw new NirhoControllerException("Problemas al obtener los cuestionarios de la empresa");
		}
		return cuestionariosTemasEmpresa;
	}
	
	@RequestMapping(value = "/consultarCuestionariosTemaEmpresa", method = RequestMethod.GET)
	@ResponseBody
	public List<CuestionarioEmpresaDTO> consultarCuestionariosTemaEmpresa(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		List<CuestionarioEmpresaDTO> cuestionariosTemasEmpresa = new ArrayList<CuestionarioEmpresaDTO>();
		try {
			cuestionariosTemasEmpresa = cuestionarioTemaEmpresaService.consultarCuestionariosTemaEmpresa(rfc);
		}catch(NirhoServiceException e){
			logger.error("Problemas al obtener los cuestionarios de la empresa" + e);
			throw new NirhoControllerException("Problemas al obtener los cuestionarios de la empresa");
		}
		return cuestionariosTemasEmpresa;
	}
	
	@RequestMapping(value = "/agregarCuestionarioEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void agregarCuestionarioEmpresa(@Valid @RequestBody CuestionarioEmpresa cuestionarioEmpresa) throws NirhoControllerException{
		try {
			cuestionarioTemaEmpresaService.agregarCuestionarioEmpresa(cuestionarioEmpresa.getEmpresa().getRfc(), cuestionarioEmpresa.getTemaCuestionario().getIdTema());
		} catch (NirhoServiceException e) {
			logger.error("Problemas al asociar el cuestionario a la empresa" + e);
			throw new NirhoControllerException("Problemas al asociar el cuestionario a la empresa");
		}
	}
	
	@RequestMapping(value = "/agregarCuestionariosEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void agregarCuestionariosEmpresa(@Valid @RequestBody List<CuestionarioEmpresa> cuestionarioEmpresaList) throws NirhoControllerException{
		for (CuestionarioEmpresa cuestionarioEmpresa : cuestionarioEmpresaList) {
			try {
				cuestionarioTemaEmpresaService.agregarCuestionarioEmpresa(cuestionarioEmpresa.getEmpresa().getRfc(), cuestionarioEmpresa.getTemaCuestionario().getIdTema());
			} catch (NirhoServiceException e) {
				logger.error("Problemas al asociar una lista de cuestionarios a la empresa" + e);
				throw new NirhoControllerException("Problemas al asociar una lista de cuestionarios a la empresa");
			}
		}
	}
	
	@RequestMapping(value = "/consultarPreguntasTemaEmpresa", method = RequestMethod.GET)
	@ResponseBody
	public List<PreguntaRespuestaCEmpresaIRHDTO> consultarPreguntasTemaEmpresa(@RequestParam(name="idCuestionarioEmpresa") Integer idCuestionarioEmpresa) throws NirhoControllerException{
		CuestionarioEmpresa cuestionarioEmpresa = null;
	    List<RespuestaPreguntaIRH> respuestas = null;
	    try
	    {
	      cuestionarioEmpresa = this.cuestionarioTemaEmpresaService.consultarPreguntasTemaEmpresa(idCuestionarioEmpresa);
	      respuestas = this.cuestionarioTemaEmpresaService.consultarRespuestasTemaEmpresa(idCuestionarioEmpresa);
	    }
	    catch (NirhoServiceException e)
	    {
	      logger.error("Problemas al obtener el cuestionario con preguntas de la empresa" + e);
	      throw new NirhoControllerException("Problemas al obtener el cuestionario con preguntas de la empresa");
	    }
	    return RespuestaCEmpresaIRHDTOTransoformer.entitiesToDTOS(cuestionarioEmpresa.getPreguntas(), respuestas);
	}
	
	@RequestMapping(value = "/agregarEditarRespuestasCuestionarioActivoEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void agregarEditarRespuestasCuestionarioActivoEmpresa(@Valid @RequestBody List<RespuestaPreguntaIRH> respuestas) throws NirhoControllerException{
		try {
			cuestionarioTemaEmpresaService.agregarEditarRespuestasCuestionarioActivoEmpresa(respuestas);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al agregar las respuestas de un cuestionario de la empresa" + e);
			throw new NirhoControllerException("Problemas al agregar las respuestas de un cuestionario de la empresa");
		}
	}
	
	@RequestMapping(value = "/agregarEditarRespuestaCuestionarioActivoEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void agregarEditarRespuestaCuestionarioActivoEmpresa(@Valid @RequestBody RespuestaPreguntaIRH respuesta) throws NirhoControllerException{
		List<RespuestaPreguntaIRH> respuestas = new ArrayList<RespuestaPreguntaIRH>();
		respuestas.add(respuesta);
		try {
			cuestionarioTemaEmpresaService.agregarEditarRespuestasCuestionarioActivoEmpresa(respuestas);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al agregar las respuestas de un cuestionario de la empresa" + e);
			throw new NirhoControllerException("Problemas al agregar las respuestas de un cuestionario de la empresa");
		}
	}
	
	@RequestMapping(value = "/eliminarRespuestasCuestionarioActivoEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void eliminarRespuestasCuestionarioActivoEmpresa(@Valid @RequestBody List<RespuestaPreguntaIRH>  respuestas) throws NirhoControllerException{
		try {
			cuestionarioTemaEmpresaService.eliminarRespuestaCuestionarioActivoEmpresa(respuestas);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al eliminar las respuestas de un cuestionario de la empresa" + e);
			throw new NirhoControllerException("Problemas al eliminar las respuestas de un cuestionario de la empresa");
		}
	}
	
	@RequestMapping(value = "/eliminarRespuestaCuestionarioActivoEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void eliminarRespuestaCuestionarioActivoEmpresa(@Valid @RequestBody RespuestaPreguntaIRH  respuesta) throws NirhoControllerException{
		List<RespuestaPreguntaIRH>  respuestas = new ArrayList<RespuestaPreguntaIRH>();
		respuestas.add(respuesta);
		try {
			cuestionarioTemaEmpresaService.eliminarRespuestaCuestionarioActivoEmpresa(respuestas);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al eliminar la respuesta de un cuestionario de la empresa" + e);
			throw new NirhoControllerException("Problemas al eliminar la respuesta de un cuestionario de la empresa");
		}
	}
	
	@RequestMapping(value = "/agregarPregunta", method = RequestMethod.POST)
	@ResponseBody
	public void agregarPregunta(@Valid @RequestBody PreguntaTema preguntaTema) throws NirhoControllerException{
		try {
//			if(preguntaTema.getIdTema() > 0){
//				preguntaTema.setTema(new TemaCuestionario(preguntaTema.getIdTema()));
//			}
			if(preguntaTema.getIdPregunta() != null && preguntaTema.getIdPregunta() > 0){
				cuestionarioTemaEmpresaService.editarPregunta(preguntaTema);
			}else{
				cuestionarioTemaEmpresaService.agregarPregunta(preguntaTema);
			}
		} catch (NirhoServiceException e) {
			logger.error("Problemas al agregar la pregunta" + e);
			throw new NirhoControllerException("Problemas al agregar la pregunta");
		}
	}
	
	@RequestMapping(value = "/editarPregunta", method = RequestMethod.POST)
	@ResponseBody
	public void editarPregunta(@Valid @RequestBody PreguntaTema preguntaTema) throws NirhoControllerException{
		try {
			cuestionarioTemaEmpresaService.editarPregunta(preguntaTema);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al editar la pregunta" + e);
			throw new NirhoControllerException("Problemas al editar la pregunta");
		}
	}
	
	@RequestMapping(value = "/agregarPreguntaCuestionario", method = RequestMethod.POST)
	@ResponseBody
	public void agregarPreguntaCuestionario(@Valid @RequestBody PreguntaCuestionarioEmpresa preguntaCuestionarioEmpresa) throws NirhoControllerException{
		try {
			//si no existe aun el cuestionarioTema asociado a una empresa se crea
			if (preguntaCuestionarioEmpresa.getCuestionarioEmpresa().getIdCuestionarioEmpresa() == null){
				CuestionarioEmpresa cuestionarioEmpresa = cuestionarioTemaEmpresaService.agregarCuestionarioEmpresa(preguntaCuestionarioEmpresa.getCuestionarioEmpresa().getEmpresa().getRfc(), preguntaCuestionarioEmpresa.getCuestionarioEmpresa().getTemaCuestionario().getIdTema());
				preguntaCuestionarioEmpresa.setCuestionarioEmpresa(cuestionarioEmpresa);
			}
			cuestionarioTemaEmpresaService.agregarPreguntaCuestionario(preguntaCuestionarioEmpresa);
		} catch (NirhoServiceException e) {
			logger.error("Problemas al agregar la pregunta al cuestionario" + e);
			throw new NirhoControllerException("Problemas al agregar la pregunta al cuestionario");
		}
	}
	
	@RequestMapping(value = "/agregarEditarTemaCuestionarioEmpresa", method = RequestMethod.POST)
	@ResponseBody
	public void agregarEditarTemaCuestionarioEmpresa(@Valid @RequestBody CuestionarioEmpresa cuestionarioEmpresa) throws NirhoControllerException{
		try {
			if(cuestionarioEmpresa.getIdCuestionarioEmpresa() == null){
				//Se agrega el tema del cuestionario
				cuestionarioEmpresa.setTemaCuestionario(cuestionarioTemaEmpresaService.agregarTema(cuestionarioEmpresa.getTemaCuestionario()));
				//Se agrega el cuestionario a la empresa
				cuestionarioEmpresa.setIdCuestionarioEmpresa((cuestionarioTemaEmpresaService.agregarCuestionarioEmpresa(cuestionarioEmpresa.getEmpresa().getRfc(), cuestionarioEmpresa.getTemaCuestionario().getIdTema())).getIdCuestionarioEmpresa());
			}

			//Se agregan las preguntas al cuestionario si no viene vacio
			if(cuestionarioEmpresa.getPreguntas() != null && !cuestionarioEmpresa.getPreguntas().isEmpty()){
				for (PreguntaCuestionarioEmpresa pregunta : cuestionarioEmpresa.getPreguntas()) {
					pregunta.setCuestionarioEmpresa(cuestionarioEmpresa);
					cuestionarioTemaEmpresaService.agregarPreguntaCuestionario(pregunta);
				}
			}
		} catch (NirhoServiceException e) {
			logger.error("Problemas al agregar el tema-cuestionario con pregunta" + e);
			throw new NirhoControllerException("Problemas al agregar el tema-cuestionario con pregunta");
		}
	}
	
	@RequestMapping(value = "/consultarPreguntasTemaCuestionario", method = RequestMethod.GET)
	@ResponseBody
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionario(@RequestParam(name="idTema") Integer idTema) throws NirhoControllerException{
		List<VwCuestionarioPreguntasTemas> preguntas = null;
	    try {
	    	preguntas = cuestionarioTemaEmpresaService.consultarPreguntasTemaCuestionario(idTema);
	    } catch (NirhoServiceException e) {
	      logger.error("Problemas al obtener las preguntas del cuestionario con idTema" + idTema + " " + e);
	      throw new NirhoControllerException("Problemas al obtener el cuestionario con preguntas de la empresa");
	    }
	    return preguntas;
	}
	
	@RequestMapping(value = "/consultarPreguntasTemaCuestionarioConRespuesta", method = RequestMethod.GET)
	@ResponseBody
	public List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionarioConRespuesta(@RequestParam(name="idTema") int idTema, 
				@RequestParam(name="idParticipante") int idParticipante) throws NirhoControllerException{
		List<VwCuestionarioPreguntasTemas> preguntas = null;
	    try {
	    	preguntas = cuestionarioTemaEmpresaService.consultarPreguntasTemaCuestionarioConRespuesta(idTema, idParticipante);
	    } catch (NirhoServiceException e) {
	      logger.error("Problemas al obtener las preguntas del cuestionario con idTema" + idTema + " idParticipante " +idParticipante +" " + e);
	      throw new NirhoControllerException("Problemas al obtener el cuestionario con preguntas de la empresa");
	    }
	    return preguntas;
	}
	
	@RequestMapping(value = "/consultarTemasCuestionario", method = RequestMethod.GET)
	@ResponseBody
	public List<VwTemaCuestionario> consultarTemasCuestionario() throws NirhoControllerException{
		List<VwTemaCuestionario> temas = null;
	    try {
	    	temas = cuestionarioTemaEmpresaService.consultarTemasCuestionario();
	    } catch (NirhoServiceException e) {
	      logger.error("Problemas al obtener los temas de cuestionarios " + e);
	      throw new NirhoControllerException("Problemas al obtener los temas de cuestionarios");
	    }
	    return temas;
	}
	

	
	@RequestMapping(value = "/agregarTemaCuestionario", method = RequestMethod.POST)
	@ResponseBody
	public void agregarTema(@Valid @RequestBody TemaCuestionario temaCuestionario) throws NirhoControllerException{
	    try {
	    	cuestionarioTemaEmpresaService.agregarTema(temaCuestionario);
	    } catch (NirhoServiceException e) {
	      logger.error("Problemas al guardar el tema " + e);
	      throw new NirhoControllerException("Problemas al guardar el tema");
	    }
	}
	
	
}
