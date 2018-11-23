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
import com.nirho.model.CuestionarioEmpresaIRH;
import com.nirho.service.CuestionarioEmpresaIRHService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionarioEmpresaIRH" )
public class CuestionarioEmpresaIRHController {
	
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaController.class);
	
	@Autowired
	CuestionarioEmpresaIRHService cuestionarioEmpresaServiceIRH;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<CuestionarioEmpresaIRH> todos() throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.listCuestionarioEmpresaIRH();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuestionarioEmpresaIRH get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHById(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
	public List<CuestionarioEmpresaIRH> getByEmpresa(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHByEmpresaId(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody CuestionarioEmpresaIRH ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.addCuestionarioEmpresaIRH(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void edit(@Valid @RequestBody CuestionarioEmpresaIRH ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRH(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/{idCuestionarioEmpresa}/finalizado/{valor}", method = RequestMethod.POST)
	public void edit(@PathVariable("idCuestionarioEmpresa") long id, @PathVariable("valor") boolean valor) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRH c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHById(id);
			c.setFinalizado(valor);
			cuestionarioEmpresaServiceIRH.updateCuestionarioEmpresaIRH(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.removeCuestionarioEmpresaIRH(cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHById(id));;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}
