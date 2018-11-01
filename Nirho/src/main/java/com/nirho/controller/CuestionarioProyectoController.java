package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.PreguntaTema;
import com.nirho.model.TemaCuestionario;
import com.nirho.service.CuestionarioProyectoService;
import com.nirho.service.TemasModuloService;

@RestController
@RequestMapping( value = "/cuestionario" )
public class CuestionarioProyectoController {
	
	@Autowired
	TemasModuloService temasService;
	@Autowired
	CuestionarioProyectoService cuestionarioService;
	
	@GetMapping(value = "/temas")
	public List<TemaCuestionario> temas(@RequestParam(name="idModulo") Integer idModulo) throws NirhoControllerException{
		List<TemaCuestionario> temas = new ArrayList<>();
		try {
			temas = temasService.obtenerTemasCuestionario(idModulo);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Sin servicio al obtener los temas del modulo");
		}
		return temas;
	}
	
	@GetMapping(value = "/preguntas")
	public List<PreguntaTema> preguntas(@RequestParam(name="idTema") Integer idTema) throws NirhoControllerException{
		List<PreguntaTema> preguntas = new ArrayList<>();
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
}
