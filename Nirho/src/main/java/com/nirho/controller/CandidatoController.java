package com.nirho.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.jboss.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
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
import com.nirho.model.Candidato;
import com.nirho.model.Usuario;
import com.nirho.service.CandidatoService;
import com.nirho.util.SessionUtil;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/candidato" )
public class CandidatoController {
	
	public final static Logger logger = Logger.getLogger(CandidatoController.class);
	 
	@Autowired
	CandidatoService candidatoService;
	
	@GetMapping(value = "/todos")
	public List<Candidato> todos() throws NirhoControllerException{
		try {
			return candidatoService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los candidatos");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Candidato get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return candidatoService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidato");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody Candidato candidato) throws NirhoControllerException{
		try {
			candidatoService.save(candidato);
			JSONObject json = new JSONObject();
			json.accumulate("id", candidato.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<Candidato> l) throws NirhoControllerException{
		try {
			candidatoService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar candidato");
		} 
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public Candidato login(@RequestBody Candidato c, HttpServletRequest request) throws NirhoControllerException {
		try {
			
			Candidato candidato = candidatoService.getOneByUsername(c.getUsername());
			if(candidato != null) {
				if(c.getPassword().equals(candidato.getPassword())) {
					return candidato;
				} else {
					throw new NirhoControllerException("Password incorrecto");
				}
			}
			throw new NirhoControllerException("Usuario incorrecto");
		} catch (NirhoControllerException e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException(e.getMessage());
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException("Problemas al conectar con la BD");
		}
	}
	
}
