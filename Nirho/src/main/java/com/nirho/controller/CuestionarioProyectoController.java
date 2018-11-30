package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.TemaPreguntas;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.EstatusProyecto;
import com.nirho.model.Pregunta;
import com.nirho.model.Proyecto;
import com.nirho.model.Tema;
import com.nirho.service.CuestionarioProyectoService;
import com.nirho.service.ProyectoService;
import com.nirho.service.TemasModuloService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionario" )
public class CuestionarioProyectoController {
	
	@Autowired
	TemasModuloService temasService;
	@Autowired
	CuestionarioProyectoService cuestionarioService;
	@Autowired
	ProyectoService proyectoService;
	
	@GetMapping(value = "/plantilla")
	public List<TemaPreguntas> plantilla(@RequestParam(name="idModulo") Integer idModulo) throws NirhoControllerException{
		List<TemaPreguntas> temas = new ArrayList<>();
		try {
			temas = temasService.obtenerPlantillaCuestionario(idModulo);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener los temas del modulo");
		}
		return temas;
	}
	
	@GetMapping(value = "/temas")
	public List<Tema> temas(@RequestParam(name="idModulo") Integer idModulo) throws NirhoControllerException{
		List<Tema> temas = new ArrayList<>();
		try {
			temas = temasService.obtenerTemasCuestionario(idModulo);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener los temas del modulo");
		}
		return temas;
	}
	
	@GetMapping(value = "/preguntas")
	public List<Pregunta> preguntas(@RequestParam(name="idTema") Integer idTema) throws NirhoControllerException{
		List<Pregunta> preguntas = new ArrayList<>();
		try {
			preguntas = temasService.obtenerPreguntasTema(idTema);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener las preguntas del tema");
		}
		return preguntas;
	}
	
	@RequestMapping(value = "/configurar", method = RequestMethod.POST)
	@ResponseBody
	public void guardarParticipantes(@RequestBody CuestionarioConfiguracion cuestionario) throws NirhoControllerException {
		try {
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(cuestionario.getIdProyecto());
			proyecto.setIdEstatus(new EstatusProyecto(ProyectoConstants.ESTATUS_CONFIGURACION));
			proyectoService.registrarProyecto(proyecto, proyecto.getIdModulo());
			cuestionarioService.guardar(cuestionario);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el cuestionario en la BD");
		}
		
	}
	
	@GetMapping(value = "/verPreguntas")
	public List<VerTemaQ> verPreguntas(@RequestParam(name="idProyecto") Integer idProyecto) throws NirhoControllerException{
		List<VerTemaQ> preguntas = new ArrayList<>();
		try {
			preguntas = cuestionarioService.verTemasCuestionario(idProyecto);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener las preguntas del tema");
		}
		return preguntas;
	}
	
	@GetMapping(value = "/participante")
	public List<CuetionarioParticipante> participante(@RequestParam(name="token") String token) throws NirhoControllerException{
		List<CuetionarioParticipante> preguntas = new ArrayList<>();
		try {
			String[] datos = token.split("-");
			Integer idProyecto = Integer.parseInt(datos[2]);
			Proyecto proyecto = proyectoService.obtenerProyectoPorId(idProyecto);
        	int estatusActual = proyecto.getIdEstatus().getIdEstatus().intValue();
        	if(estatusActual >= ProyectoConstants.ESTATUS_FINALIZADO.intValue()) {
        		throw new NirhoControllerException("Se ha finalizado el esdudio del proyecto");
        	}
			preguntas = cuestionarioService.obtenerCuestionarioParticipante(token);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener las preguntas del tema");
		}
		return preguntas;
	}
	
	@RequestMapping(value = "/contestaPregPart", method = RequestMethod.POST)
	@ResponseBody
	public void contestaPregPart(@RequestBody CuetionarioParticipante questPart) throws NirhoControllerException {
		try {
			cuestionarioService.contestarPregunta(questPart);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el cuestionario en la BD");
		}
		
	}
}
