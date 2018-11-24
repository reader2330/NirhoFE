package com.nirho.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.GraficaProyectoDTO;
import com.nirho.dto.GraficaRespPregDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.GraficaProyecto;
import com.nirho.service.GraficasProyectoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/graficas" )
public class GraficasProyectoController {
	public final static Logger logger = Logger.getLogger(GraficasProyectoController.class);
	
	@Autowired
	GraficasProyectoService graficasService;
		
	@RequestMapping(value = "/respuestas", method = RequestMethod.GET)
	@ResponseBody
	public GraficaRespPregDTO proyecto(@RequestParam(name="idProyecto") String idProyecto) throws NirhoControllerException{
		GraficaRespPregDTO graficas = new GraficaRespPregDTO();
		 logger.info("***************[idProyecto]******" + idProyecto);
		try {
			graficas = graficasService.obtenerGraficasRespuestasPreguntas(Integer.parseInt(idProyecto));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return graficas;
	}
	
	@RequestMapping(value = "/proyecto", method = RequestMethod.GET)
	@ResponseBody
	public GraficaProyectoDTO proyectoTema(@RequestParam(name="idProyecto") String idProyecto) throws NirhoControllerException{
		GraficaProyectoDTO graficas = new GraficaProyectoDTO();
		 logger.info("***************[idProyecto]******" + idProyecto);
		try {
			graficas = graficasService.obtenerGraficasProyecto(Integer.parseInt(idProyecto));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return graficas;
	}
	
	@RequestMapping(value = "/guardarComentario", method = RequestMethod.POST)
	@ResponseBody
	public void guardarComentario(@RequestBody GraficaProyecto graficaProyecto) throws NirhoControllerException {
		logger.info(" ********************************* GraficaProyecto [" + graficaProyecto + "] *****************************");
		try {
			graficasService.guardarComentario(graficaProyecto);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar el comentario en la BD");
		}
	}
}
