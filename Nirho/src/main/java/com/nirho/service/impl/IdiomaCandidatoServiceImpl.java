package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.IdiomaCandidatoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.IdiomaCandidato;
import com.nirho.service.IdiomaCandidatoService;

@Service
public class IdiomaCandidatoServiceImpl implements IdiomaCandidatoService {
	
	public final static Logger logger = Logger.getLogger(IdiomaCandidatoServiceImpl.class);
	
	@Autowired
	private IdiomaCandidatoDAO idiomaCandidatoDAO;
	
	public void editar(IdiomaCandidato entidad) throws NirhoServiceException{
		try {
			idiomaCandidatoDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			IdiomaCandidato entidad = idiomaCandidatoDAO.getOne(id);
			if(entidad != null) {
				idiomaCandidatoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public IdiomaCandidato getOne(Long id) throws NirhoServiceException{
		try {
			return idiomaCandidatoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<IdiomaCandidato> getAll() throws NirhoServiceException{
		try {
			return idiomaCandidatoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
