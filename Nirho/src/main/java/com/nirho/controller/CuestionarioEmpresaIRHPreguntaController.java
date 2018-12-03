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
import com.nirho.model.CuestionarioEmpresaIRHPregunta;
import com.nirho.service.CuestionarioEmpresaIRHPreguntaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionarioEmpresaIRHPregunta" )
public class CuestionarioEmpresaIRHPreguntaController {
	
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaIRHPreguntaController.class);
	
	@Autowired
	CuestionarioEmpresaIRHPreguntaService cuestionarioEmpresaServiceIRH;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<CuestionarioEmpresaIRHPregunta> todos() throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.listCuestionarioEmpresaIRHPregunta();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuestionarioEmpresaIRHPregunta get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHPreguntaById(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de respuesta");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody CuestionarioEmpresaIRHPregunta ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.addCuestionarioEmpresaIRHPregunta(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void edit(@Valid @RequestBody CuestionarioEmpresaIRHPregunta ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRHPregunta(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/{idPregunta}/respuesta1/{valor}", method = RequestMethod.POST)
	public void editPregunta1(@PathVariable("idPregunta") long id, @PathVariable("valor") int valor) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRHPregunta c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHPreguntaById(id);
			c.setRespuesta1(valor);
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRHPregunta(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/{idPregunta}/respuesta2/{valor}", method = RequestMethod.POST)
	public void editPregunta2(@PathVariable("idPregunta") long id, @PathVariable("valor") int valor) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRHPregunta c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHPreguntaById(id);
			c.setRespuesta2(valor);
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRHPregunta(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/{idPregunta}/respuesta3/{valor}", method = RequestMethod.POST)
	public void editPregunta3(@PathVariable("idPregunta") long id, @PathVariable("valor") int valor) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRHPregunta c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHPreguntaById(id);
			c.setRespuesta3(valor);
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRHPregunta(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar respuesta");
		} 
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.removeCuestionarioEmpresaIRHPregunta(cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHPreguntaById(id));;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}
