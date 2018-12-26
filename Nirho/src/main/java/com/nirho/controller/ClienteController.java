package com.nirho.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.nirho.model.Cliente;
import com.nirho.model.ModulosCliente;
import com.nirho.service.ClienteService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cliente" )
public class ClienteController {

	public final static Logger logger = Logger.getLogger(ClienteController.class);

	@Autowired
	ClienteService clienteService;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<Cliente> todas() throws NirhoControllerException{
		try {
			return clienteService.list();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros de cliente");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cliente get(@PathVariable("id") int id) throws NirhoControllerException{
		try {
			Cliente e = clienteService.getClienteById(id);
			return e;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cliente");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody Cliente e) throws NirhoControllerException{
		try {
			clienteService.add(e);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cliente");
		} 
	}

	@RequestMapping(value = "/editar", method = RequestMethod.POST)
    public void edit(@Valid @RequestBody Cliente e)  throws NirhoControllerException{
		try {	
			clienteService.update(e);		
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al actualizar registro de cliente");
		} 
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") int id) throws NirhoControllerException{
		try {			
			clienteService.remove(clienteService.getClienteById(id));
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de cliente");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de cliente");
		}
	}
	
	@RequestMapping(value = "/modulos", method = RequestMethod.GET)
	public void getModulos() throws NirhoControllerException{
		try {
			clienteService.list().get(0).getModulos();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cliente");
		} 
	}
	
	@RequestMapping(value = "/modulos/checked", method = RequestMethod.GET)
	public List<ModulosCliente> getModulosChecked() throws NirhoControllerException{
		try {
			List<ModulosCliente> list = new ArrayList<>();
			for(ModulosCliente modulo: clienteService.list().get(0).getModulos()){
				if(modulo.isCheckModulo()) {
					list.add(modulo);
				}
			}
			return list;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cliente");
		} 
	}
	
	@RequestMapping(value = "/modulos/editar", method = RequestMethod.POST)
	public void editModulos(@Valid @RequestBody Set<ModulosCliente> l) throws NirhoControllerException{
		try {
			Cliente e = clienteService.list().get(0); 
			e.setModulos(l);
			clienteService.update(e);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cliente");
		} 
	}
	
}
