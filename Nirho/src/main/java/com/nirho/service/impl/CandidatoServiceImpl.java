package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CandidatoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Candidato;
import com.nirho.service.CandidatoService;

@Service
public class CandidatoServiceImpl implements CandidatoService {
	  
	public final static Logger logger = Logger.getLogger(CandidatoServiceImpl.class);
	
	@Autowired
	private CandidatoDAO candidatoDAO;
	
	public void save(Candidato entidad) throws NirhoServiceException{
		try {
			Candidato s = candidatoDAO.getOne(entidad.getId());
			if(s == null) {
				candidatoDAO.save(entidad);
			} else {
				candidatoDAO.update(entidad);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void save(List<Candidato> l) throws NirhoServiceException{
		try {
			for(Candidato entidad: l) {
				Candidato s = candidatoDAO.getOne(entidad.getId());
				if(s == null) {
					candidatoDAO.save(entidad);
				} else {
					candidatoDAO.update(entidad);
				}
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			Candidato entidad = candidatoDAO.getOne(id);
			if(entidad != null) {
				candidatoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public Candidato getOne(Long id) throws NirhoServiceException{
		try {
			return candidatoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public Candidato getOneByUsername(String username) throws NirhoServiceException{
		try {
			return candidatoDAO.findByUsername(username).get(0);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<Candidato> getAll() throws NirhoServiceException{
		try {
			return candidatoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
