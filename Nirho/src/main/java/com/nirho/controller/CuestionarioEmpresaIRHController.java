package com.nirho.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRH;
import com.nirho.model.CuestionarioEmpresaIRHPregunta;
import com.nirho.model.CuestionarioEmpresaIRHTema;
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
	
	@RequestMapping(value = "/preguntas/predeterminadas", method = RequestMethod.GET)
	public Set<CuestionarioEmpresaIRHTema> getDefaultPreguntas() throws NirhoControllerException{
		try {
			List<CuestionarioEmpresaIRH> cuestionarios = cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHByEmpresaId(0);
			if(cuestionarios.size() > 0) {
				Set<CuestionarioEmpresaIRHTema> newTemas = new HashSet<>();
				for(CuestionarioEmpresaIRHTema tema : cuestionarios.get(0).getTemas()) {
					CuestionarioEmpresaIRHTema newTema = new CuestionarioEmpresaIRHTema();
					newTema.setNombre(tema.getNombre());
					newTema.setStatus(true);
					Set<CuestionarioEmpresaIRHPregunta> newPreguntas = new HashSet<>();
					for(CuestionarioEmpresaIRHPregunta pregunta : tema.getPreguntas()) {
						CuestionarioEmpresaIRHPregunta newPregunta = new CuestionarioEmpresaIRHPregunta();
						newPregunta.setEnunciado(pregunta.getEnunciado());
						newPreguntas.add(newPregunta);
					}
					newTema.setPreguntas(newPreguntas);
					newTemas.add(newTema);
				}
				return newTemas;
			} else {
				throw new NirhoControllerException("");
			}
			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/empresa/{id}/score", method = RequestMethod.GET)
	public String getScoreByEmpresa(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			List<CuestionarioEmpresaIRH> l = cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHByEmpresaId(id);
			return "{\"score\": " + l.get(0).getScore() + "}";
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public String add(@Valid @RequestBody CuestionarioEmpresaIRH ce) throws NirhoControllerException{
		try {
			cuestionarioEmpresaServiceIRH.addCuestionarioEmpresaIRH(ce);	
			JSONObject response = new JSONObject();
			response.accumulate("id", ce.getId());
			return response.toString();
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
	

	@RequestMapping(value = "/{idCuestionarioEmpresa}/finalizado/{valor}/score/{valorcore}", method = RequestMethod.POST)
	public void edit(@PathVariable("idCuestionarioEmpresa") long id, @PathVariable("valor") boolean valor, @PathVariable("valorcore") double score) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRH c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHById(id);
			c.setFinalizado(valor);
			c.setScore(score);
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
	
	@RequestMapping(value = "/empresa/{id}/scores", method = RequestMethod.GET)
    public String empresaScores(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			List<CuestionarioEmpresaIRH> l = cuestionarioEmpresaServiceIRH.getListCuestionarioEmpresaIRHByEmpresaId(id);
			CuestionarioEmpresaIRH c = l.get(0);
			Set<CuestionarioEmpresaIRHTema> temas = c.getTemas();		
			JSONObject jsonCuestionario = new JSONObject();
			jsonCuestionario.accumulate("id", c.getId());
			jsonCuestionario.accumulate("idEmpresa", c.getIdEmpresa());
			jsonCuestionario.accumulate("score", c.getScore());
			JSONArray jsonTemas = new JSONArray();
			for (CuestionarioEmpresaIRHTema t : temas) {
			    JSONObject jsonTema = new JSONObject();
			    jsonTema.accumulate("idTema", t.getIdTema());
			    jsonTema.accumulate("nombre", t.getNombre());
			    jsonTema.accumulate("score", t.getScore());
			    jsonTemas.put(jsonTema);
			}
			jsonCuestionario.put("temas", jsonTemas);
			return jsonCuestionario.toString();	
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
	@RequestMapping(value = "/{id}/scores", method = RequestMethod.GET)
    public String cuestionarioScores(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			CuestionarioEmpresaIRH c = cuestionarioEmpresaServiceIRH.getCuestionarioEmpresaIRHById(id);
			Set<CuestionarioEmpresaIRHTema> temas = c.getTemas();		
			JSONObject jsonCuestionario = new JSONObject();
			jsonCuestionario.accumulate("id", c.getId());
			jsonCuestionario.accumulate("idEmpresa", c.getIdEmpresa());
			jsonCuestionario.accumulate("score", c.getScore());
			JSONArray jsonTemas = new JSONArray();
			for (CuestionarioEmpresaIRHTema t : temas) {
			    JSONObject jsonTema = new JSONObject();
			    jsonTema.accumulate("idTema", t.getIdTema());
			    jsonTema.accumulate("nombre", t.getNombre());
			    jsonTema.accumulate("score", t.getScore());
			    jsonTemas.put(jsonTema);
			}
			jsonCuestionario.put("temas", jsonTemas);
			return jsonCuestionario.toString();			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al obtener cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al obtener cuestionario empresa");
		} 
	}
	
}
