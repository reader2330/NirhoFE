package com.nirho.controller;

import java.util.List;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Solicitante;
import com.nirho.model.SolicitanteContacto;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.SolicitanteService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/solicitante" )
public class SolicitanteController {
	
	public final static Logger logger = Logger.getLogger(SolicitanteController.class);
	 
	@Autowired
	SolicitanteService solicitanteService;
	
	@GetMapping(value = "/todos")
	public List<Solicitante> todos() throws NirhoControllerException{
		try {
			return solicitanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@GetMapping(value = "/porRFC")
	public List<Solicitante> porConsultor(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		try {
			return solicitanteService.getByRFC(rfc);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los solicitantes");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Solicitante get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return solicitanteService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de solicitante");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody Solicitante solicitante) throws NirhoControllerException{
		try {
			solicitanteService.save(solicitante);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<Solicitante> l) throws NirhoControllerException{
		try {
			solicitanteService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/contacto/guardar", method = RequestMethod.POST)
	public void add(@PathVariable("id") int id, @Valid @RequestBody SolicitanteContacto l) throws NirhoControllerException{
		try {
			Solicitante s = solicitanteService.getOne(id);
			if(s != null) {
				s.getContactos().add(l);
				solicitanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/vacante/guardar", method = RequestMethod.POST)
	public void add(@PathVariable("id") int id, @Valid @RequestBody SolicitanteVacante l) throws NirhoControllerException{
		try {
			Solicitante s = solicitanteService.getOne(id);
			if(s != null) {
				s.getVacantes().add(l);
				solicitanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
}
