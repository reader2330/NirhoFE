package com.nirho.controller;

import java.util.List;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyectoTPPregunta;
import com.nirho.service.CuestionarioProyectoTPPreguntaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionarioProyectoTPPregunta" )
public class CuestionarioProyectoTPPreguntaController {
	
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoTPPreguntaController.class);
	
	@Autowired
	CuestionarioProyectoTPPreguntaService cuestionarioProyectoServiceTP;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<CuestionarioProyectoTPPregunta> todos() throws NirhoControllerException{
		try {
			return cuestionarioProyectoServiceTP.listCuestionarioProyectoTPPregunta();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuestionarioProyectoTPPregunta get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioProyectoServiceTP.getCuestionarioProyectoTPPreguntaById(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de respuesta");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody CuestionarioProyectoTPPregunta ce) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.addCuestionarioProyectoTPPregunta(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void edit(@Valid @RequestBody CuestionarioProyectoTPPregunta ce) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.updateCuestionarioProyectoTPPregunta(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/{idPregunta}/respuesta/{valor}", method = RequestMethod.POST)
	public void edit(@PathVariable("idPregunta") long id, @PathVariable("valor") int valor) throws NirhoControllerException{
		try {
			CuestionarioProyectoTPPregunta c = cuestionarioProyectoServiceTP.getCuestionarioProyectoTPPreguntaById(id);
			c.setRespuesta(valor);
			cuestionarioProyectoServiceTP.updateCuestionarioProyectoTPPregunta(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.removeCuestionarioProyectoTPPregunta(cuestionarioProyectoServiceTP.getCuestionarioProyectoTPPreguntaById(id));;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}
