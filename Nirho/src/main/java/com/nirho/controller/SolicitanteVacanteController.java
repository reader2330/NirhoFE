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
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.model.CaracteristicasCandidatoVacante;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoVacante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.SolicitanteVacanteService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/vacante" )
public class SolicitanteVacanteController {
	
	public final static Logger logger = Logger.getLogger(SolicitanteController.class);
	 
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	
	@GetMapping(value = "/todos")
	public List<SolicitanteVacante> todos() throws NirhoControllerException{
		try {
			return solicitanteVacanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SolicitanteVacante get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return solicitanteVacanteService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de entidad");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody SolicitanteVacante e) throws NirhoControllerException{
		try {
			solicitanteVacanteService.editar(e);
			JSONObject json = new JSONObject();
			json.accumulate("id", e.getId());
			return json.toString();
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	public void delete(@Valid @RequestBody long id) throws NirhoControllerException{
		try {
			solicitanteVacanteService.eliminar(id);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/actividades/guardar", method = RequestMethod.POST)
	public void addAct(@PathVariable("id") long id, @Valid @RequestBody ActividadesPuestoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getActividades().add(l);
				solicitanteVacanteService.editar(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/conocimientos/guardar", method = RequestMethod.POST)
	public void addConocimientos(@PathVariable("id") long id, @Valid @RequestBody ConocimientoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getConocimientos().add(l);
				solicitanteVacanteService.editar(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/competencias/guardar", method = RequestMethod.POST)
	public void addCompetencias(@PathVariable("id") long id, @Valid @RequestBody CompetenciasVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCompetencias().add(l);
				solicitanteVacanteService.editar(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
	@RequestMapping(value = "/{id}/caracteristicas/guardar", method = RequestMethod.POST)
	public void addCaracteristicas(@PathVariable("id") long id, @Valid @RequestBody CaracteristicasCandidatoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCaracteristicas().add(l);
				solicitanteVacanteService.editar(s);
			}
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} 
	}
	
}
