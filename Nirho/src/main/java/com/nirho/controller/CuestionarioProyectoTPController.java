package com.nirho.controller;

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
import com.nirho.model.CuestionarioProyectoTP;
import com.nirho.model.CuestionarioProyectoTPTema;
import com.nirho.service.CuestionarioProyectoTPService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/cuestionarioProyectoTP" )
public class CuestionarioProyectoTPController {
	
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoTPController.class);
	
	@Autowired
	CuestionarioProyectoTPService cuestionarioProyectoServiceTP;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	public List<CuestionarioProyectoTP> todos() throws NirhoControllerException{
		try {
			return cuestionarioProyectoServiceTP.listCuestionarioProyectoTP();			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los registros ");
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CuestionarioProyectoTP get(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioProyectoServiceTP.getCuestionarioProyectoTPById(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/empresa/{id}", method = RequestMethod.GET)
	public List<CuestionarioProyectoTP> getByProyecto(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			return cuestionarioProyectoServiceTP.getListCuestionarioProyectoTPByProyectoId(id);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/preguntas/predeterminadas", method = RequestMethod.GET)
	public Set<CuestionarioProyectoTPTema> getDefaultPreguntas() throws NirhoControllerException{
		try {
			List<CuestionarioProyectoTP> cuestionarios = cuestionarioProyectoServiceTP.getListCuestionarioProyectoTPByProyectoId(0);
			if(cuestionarios.size() > 0) {
				Set<CuestionarioProyectoTPTema> temas = cuestionarios.get(0).getTemas();
				return temas;
			} else {
				throw new NirhoControllerException("");
			}
			
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/empresa/{id}/score", method = RequestMethod.GET)
	public String getScoreByProyecto(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			List<CuestionarioProyectoTP> l = cuestionarioProyectoServiceTP.getListCuestionarioProyectoTPByProyectoId(id);
			return "{\"score\": " + l.get(0).getScore() + "}";
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de cuestionario empresa");
		}
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public void add(@Valid @RequestBody CuestionarioProyectoTP ce) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.addCuestionarioProyectoTP(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public void edit(@Valid @RequestBody CuestionarioProyectoTP ce) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.updateCuestionarioProyectoTP(ce);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	

	@RequestMapping(value = "/{idCuestionarioProyecto}/finalizado/{valor}/score/{valorcore}", method = RequestMethod.POST)
	public void edit(@PathVariable("idCuestionarioProyecto") long id, @PathVariable("valor") boolean valor, @PathVariable("valorcore") double score) throws NirhoControllerException{
		try {
			CuestionarioProyectoTP c = cuestionarioProyectoServiceTP.getCuestionarioProyectoTPById(id);
			c.setFinalizado(valor);
			c.setScore(score);;
			cuestionarioProyectoServiceTP.updateCuestionarioProyectoTP(c);			
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} catch(Exception exe) {
			throw new NirhoControllerException("Problemas al registrar cuestionario empresa");
		} 
	}
	 
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.DELETE)
    public void removePerson(@PathVariable("id") long id) throws NirhoControllerException{
		try {
			cuestionarioProyectoServiceTP.removeCuestionarioProyectoTP(cuestionarioProyectoServiceTP.getCuestionarioProyectoTPById(id));;
		} catch(NirhoServiceException ex){
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		} catch(Exception e) {
			throw new NirhoControllerException("Problemas al eliminar el registro de empleado");
		}
	}
	
}
