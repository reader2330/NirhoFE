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
import com.nirho.model.CuestionarioEmpresaIRHTema;
import com.nirho.service.CuestionarioEmpresaIRHTemaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionarioEmpresaIRHTema" )
public class CuestionarioEmpresaIRHTemaController {
	
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaController.class);
	
	@Autowired
	CuestionarioEmpresaIRHTemaService cuestionarioEmpresaServiceIRH;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<CuestionarioEmpresaIRHTema> todos() throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.listCuestionarioEmpresaIRHTema();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuestionarioEmpresaIRHTema get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHTemaById(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
	public List<CuestionarioEmpresaIRHTema> getByEmpresa(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHTemaByEmpresaId(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody CuestionarioEmpresaIRHTema ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.addCuestionarioEmpresaIRHTema(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void edit(@Valid @RequestBody CuestionarioEmpresaIRHTema ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRHTema(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.removeCuestionarioEmpresaIRHTema(cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHTemaById(id));;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}
