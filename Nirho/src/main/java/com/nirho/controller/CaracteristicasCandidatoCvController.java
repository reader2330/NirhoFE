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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CaracteristicasCandidatoCv;
import com.nirho.model.Solicitante;
import com.nirho.model.SolicitanteContacto;
import com.nirho.service.CaracteristicasCandidatoCvService;
import com.nirho.service.SolicitanteService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping( value = "/caracteristicasCandidatoCv" )
public class CaracteristicasCandidatoCvController {
	
	public final static Logger logger = Logger.getLogger(CaracteristicasCandidatoCvController.class);
	 
	@Autowired
	CaracteristicasCandidatoCvService caracteristicasCandidatoCvService;
	
	@GetMapping(value = "/todos")
	public List<CaracteristicasCandidatoCv> todos() throws NirhoControllerException{
		try {
			return caracteristicasCandidatoCvService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los entidads");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CaracteristicasCandidatoCv get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return caracteristicasCandidatoCvService.getOne(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de entidad");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody CaracteristicasCandidatoCv e) throws NirhoControllerException{
		try {
			caracteristicasCandidatoCvService.editar(e);
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
			caracteristicasCandidatoCvService.eliminar(id);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
}