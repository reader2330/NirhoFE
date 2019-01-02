package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ContactoCandidatoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ContactoCandidato;
import com.nirho.service.ContactoCandidatoService;

@Service
public class ContactoCandidatoServiceImpl implements ContactoCandidatoService {
	
	public final static Logger logger = Logger.getLogger(ContactoCandidatoServiceImpl.class);
	
	@Autowired
	private ContactoCandidatoDAO contactoCandidatoDAO;
	
	public void editar(ContactoCandidato entidad) throws NirhoServiceException{
		try {
			contactoCandidatoDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ContactoCandidato entidad = contactoCandidatoDAO.getOne(id);
			if(entidad != null) {
				contactoCandidatoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ContactoCandidato getOne(Long id) throws NirhoServiceException{
		try {
			return contactoCandidatoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ContactoCandidato> getAll() throws NirhoServiceException{
		try {
			return contactoCandidatoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
