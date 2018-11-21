package com.nirho.controller;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.GraficasProyectoDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.service.GraficasProyectoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/graficas" )
public class GraficasProyectoController {
	public final static Logger logger = Logger.getLogger(GraficasProyectoController.class);
	
	@Autowired
	GraficasProyectoService graficasService;
		
	@RequestMapping(value = "/proyecto", method = RequestMethod.GET)
	@ResponseBody
	public GraficasProyectoDTO proyecto(@RequestParam(name="idProyecto") String idProyecto) throws NirhoControllerException{
		GraficasProyectoDTO graficas = new GraficasProyectoDTO();
		 logger.info("***************[idProyecto]******" + idProyecto);
		try {
			graficas = graficasService.obtenerGraficasProyecto(Integer.parseInt(idProyecto));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro del proyecto");
		}
		return graficas;
	}
}
