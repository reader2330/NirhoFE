package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ExperienciaCandidatoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ExperienciaCandidato;
import com.nirho.service.ExperienciaCandidatoService;

@Service
public class ExperienciaCandidatoServiceImpl implements ExperienciaCandidatoService {
	
	public final static Logger logger = Logger.getLogger(ExperienciaCandidatoServiceImpl.class);
	
	@Autowired
	private ExperienciaCandidatoDAO experienciaCandidatoDAO;
	
	public void editar(ExperienciaCandidato entidad) throws NirhoServiceException{
		try {
			experienciaCandidatoDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ExperienciaCandidato entidad = experienciaCandidatoDAO.getOne(id);
			if(entidad != null) {
				experienciaCandidatoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ExperienciaCandidato getOne(Long id) throws NirhoServiceException{
		try {
			return experienciaCandidatoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ExperienciaCandidato> getAll() throws NirhoServiceException{
		try {
			return experienciaCandidatoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
