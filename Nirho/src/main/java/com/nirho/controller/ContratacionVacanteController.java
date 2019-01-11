package com.nirho.controller;

import java.util.List;


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
import org.springframework.web.bind.annotation.RestController;

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ContratacionVacante;
import com.nirho.service.ContratacionVacanteService;
import com.nirho.service.SolicitanteVacanteService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/contratacionVacante" )
public class ContratacionVacanteController {
	
	public final static Logger logger = Logger.getLogger(ContratacionVacanteController.class);
	 
	@Autowired
	ContratacionVacanteService contratacionVacanteService;
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	
	@GetMapping(value = "/todos")
	public List<ContratacionVacante> todos() throws NirhoControllerException{
		try {
			return contratacionVacanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los contratacionVacantes");
		}
	}
	
	@GetMapping(value = "/conteo")
	public String count() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			response.accumulate("total", contratacionVacanteService.getAll().size());
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los contratacionVacantes");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de los contratacionVacantes");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ContratacionVacante get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return contratacionVacanteService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de contratacionVacante");
		}
	}
	
	@RequestMapping(value = "/{id}/aceptado/{aceptado}", method = RequestMethod.GET)
	public void setStatus(@PathVariable("id") long id, @PathVariable("aceptado") boolean aceptado) throws NirhoControllerException{
		try {
			ContratacionVacante c = contratacionVacanteService.getOne(id);
			if(c != null) {
				c.setAceptado(aceptado);
				contratacionVacanteService.save(c);
			}
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de contratacionVacante");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody ContratacionVacante contratacionVacante) throws NirhoControllerException{
		try {
			contratacionVacanteService.save(contratacionVacante);
			JSONObject json = new JSONObject();
			json.accumulate("id", contratacionVacante.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<ContratacionVacante> l) throws NirhoControllerException{
		try {
			contratacionVacanteService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar contratacionVacante");
		} 
	}
	
	@RequestMapping(value = "/{id}/candidato/{idCandidato}", method = RequestMethod.POST)
	public void setConsultor(@PathVariable("id") long id, @PathVariable("idCandidato") long idCandidato) throws NirhoControllerException{
		try {
			ContratacionVacante s = contratacionVacanteService.getOne(id);
			if(s != null) {
				s.setIdCandidato(idCandidato);
				contratacionVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}

	@RequestMapping(value = "/{id}/vacante/{idVacante}", method = RequestMethod.POST)
	public void setSolicitante(@PathVariable("id") long id, @PathVariable("idVacante") long idVacante) throws NirhoControllerException{
		try {
			ContratacionVacante s = contratacionVacanteService.getOne(id);
			if(s != null) {
				s.setIdVacante(idVacante);
				contratacionVacanteService.save(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}

	
}
