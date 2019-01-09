package com.nirho.controller;

import java.util.ArrayList;
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

import com.nirho.dto.SolicitanteVacanteDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.model.Candidato;
import com.nirho.model.CaracteristicasCandidatoVacante;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoVacante;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.CandidatoService;
import com.nirho.service.SolicitanteVacanteService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/vacante" )
public class SolicitanteVacanteController {
	
	public final static Logger logger = Logger.getLogger(SolicitanteController.class);
	 
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	@Autowired
	CandidatoService candidatoService;
	
	@GetMapping(value = "/todos")
	public List<SolicitanteVacante> todos() throws NirhoControllerException{
		try {
			return solicitanteVacanteService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@GetMapping(value = "/todos/candidatos")
	public List<SolicitanteVacanteDTO> todosConCandidatos() throws NirhoControllerException{
		try {
			List<SolicitanteVacanteDTO> response = new ArrayList<>();
			for(SolicitanteVacante v: solicitanteVacanteService.getAll()) {
				List<Candidato> candidatos = candidatoService.getAllByVacante(v.getId());
				SolicitanteVacanteDTO s = new SolicitanteVacanteDTO(v.getId(), v.getAniosExperiencia(), v.getEstadoVacante(), v.getGiro(), v.getMotivo(), v.getStatus(), v.getNombreVacante(), v.getNumVacantes(), v.getPuesto(), v.getPuestoCargo(), v.getPuestoReporta(), candidatos, v.getActividades() , v.getCaracteristicas(), v.getCompetencias(), v.getConocimientos());
			}
			return response;
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@GetMapping(value = "/conteo")
	public String count() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			response.accumulate("total", solicitanteVacanteService.getAll().size());
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@GetMapping(value = "/totales")
	public String totales() throws NirhoControllerException{
		try {
			JSONObject response = new JSONObject();
			int abiertas = 0;
			int asignadas = 0;
			for(SolicitanteVacante v: solicitanteVacanteService.getAll()) {
				if(v.getStatus() == 1) {
					abiertas++;
				}
				if(v.getStatus() == 2) {
					asignadas++;
				}
			}
			response.accumulate("abiertas", abiertas);
			response.accumulate("asignadas", asignadas);
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		} catch (JSONException e) {
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
	public String addAct(@PathVariable("id") long id, @Valid @RequestBody ActividadesPuestoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getActividades().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/conocimientos/guardar", method = RequestMethod.POST)
	public String addConocimientos(@PathVariable("id") long id, @Valid @RequestBody ConocimientoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getConocimientos().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/competencias/guardar", method = RequestMethod.POST)
	public String addCompetencias(@PathVariable("id") long id, @Valid @RequestBody CompetenciasVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCompetencias().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/caracteristicas/guardar", method = RequestMethod.POST)
	public String addCaracteristicas(@PathVariable("id") long id, @Valid @RequestBody CaracteristicasCandidatoVacante l) throws NirhoControllerException{
		try {
			SolicitanteVacante s = solicitanteVacanteService.getOne(id);
			if(s != null) {
				s.getCaracteristicas().add(l);
				solicitanteVacanteService.editar(s);
				JSONObject json = new JSONObject();
				json.accumulate("id", l.getId());
				return json.toString();
			}
			return null;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar solicitante");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
}
