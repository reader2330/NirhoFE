package com.nirho.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.json.JSONArray;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.CandidatoDocumentoDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CandidatoDocumento;
import com.nirho.service.CandidatoDocumentoService;
import com.nirho.service.ContratacionVacanteService;
import com.nirho.service.SolicitanteVacanteService;
import com.nirho.service.UsuarioService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/candidatoDocumento" )
public class CandidatoDocumentoController {
	
	public final static Logger logger = Logger.getLogger(CandidatoDocumentoController.class);
	 
	@Autowired
	CandidatoDocumentoService candidatoDocumentoService;
	@Autowired
	SolicitanteVacanteService solicitanteVacanteService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	ContratacionVacanteService contratacionVacanteService;
	
	@GetMapping(value = "/todos")
	public List<CandidatoDocumento> todos() throws NirhoControllerException{
		try {
			return candidatoDocumentoService.getAll();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de los candidatoDocumentos");
		}
	}
	
	@RequestMapping(value = "/{id}/descargar", method = RequestMethod.GET)
	@ResponseBody
	public void get(@PathVariable("id") long id, HttpServletResponse response) throws NirhoControllerException{
		try {
			
			CandidatoDocumento cd = candidatoDocumentoService.getOne(id);
			
	        response.setContentType("application/octet-stream"); 
	        response.setHeader("Content-Disposition", "attachment; filename=" + cd.getNombre());
	        OutputStream outStream = response.getOutputStream();
	        outStream.write(cd.getFile());
	        response.flushBuffer();
			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidatoDocumento");
		} catch (IOException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de candidatoDocumento");
		}
	}
	
	@GetMapping(value = "/porCandidato")
	public String porRFC(@RequestParam(name="idCandidato") long idCandidato) throws NirhoControllerException{
		try {
			JSONArray response = new JSONArray();
			List<CandidatoDocumento> l = candidatoDocumentoService.getAllByCandidato(idCandidato);
			for(CandidatoDocumento cd : l) {
				JSONObject json = new JSONObject();
				json.accumulate("id", cd.getId());
				json.accumulate("nombre", cd.getNombre());
				response.put(json);
			}
			return response.toString();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidatoDocumento");
		} catch (JSONException e) {
			throw new NirhoControllerException("Problemas al obtener el registro de candidatoDocumento");
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody CandidatoDocumentoDTO candidatoDocumento) throws NirhoControllerException{
		try {
			CandidatoDocumento cd = new CandidatoDocumento(candidatoDocumento.getId(), candidatoDocumento.getIdCandidato(), candidatoDocumento.getNombre(), new Base64().decode(candidatoDocumento.getFile()));
			candidatoDocumentoService.save(cd);
			JSONObject json = new JSONObject();
			json.accumulate("id", cd.getId());
			return json.toString();		
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar entidad");
		} catch (JSONException e1) {
			throw new NirhoControllerException("Problemas al registrar entidad");
		} 
	}
	
	@RequestMapping(value = "/{id}/eliminar", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			candidatoDocumentoService.eliminar(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de candidatoDocumento");
		}
	}
	
	@RequestMapping(value = "/guardarTodos", method = RequestMethod.POST)
	public void add(@Valid @RequestBody List<CandidatoDocumento> l) throws NirhoControllerException{
		try {
			candidatoDocumentoService.save(l);
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar candidatoDocumento");
		} 
	}
	
}
