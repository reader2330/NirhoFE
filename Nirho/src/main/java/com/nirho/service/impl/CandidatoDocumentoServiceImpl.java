package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CandidatoDocumentoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CandidatoDocumento;
import com.nirho.service.CandidatoDocumentoService;

@Service
public class CandidatoDocumentoServiceImpl implements CandidatoDocumentoService {
	  
	public final static Logger logger = Logger.getLogger(CandidatoDocumentoServiceImpl.class);
	
	@Autowired
	private CandidatoDocumentoDAO candidatoDocumentoDAO;
	
	public void save(CandidatoDocumento entidad) throws NirhoServiceException{
		try {
			
			CandidatoDocumento s = candidatoDocumentoDAO.getOne(entidad.getId());
			if(s == null) {
				candidatoDocumentoDAO.save(entidad);
			} else {
				candidatoDocumentoDAO.update(entidad);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void save(List<CandidatoDocumento> l) throws NirhoServiceException{
		try {
			for(CandidatoDocumento entidad: l) {
				CandidatoDocumento s = candidatoDocumentoDAO.getOne(entidad.getId());
				if(s == null) {
					candidatoDocumentoDAO.save(entidad);
				} else {
					candidatoDocumentoDAO.update(entidad);
				}
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(long id) throws NirhoServiceException{
		try {
			CandidatoDocumento entidad = candidatoDocumentoDAO.getOne(id);
			if(entidad != null) {
				candidatoDocumentoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public CandidatoDocumento getOne(long id) throws NirhoServiceException{
		try {
			return candidatoDocumentoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	
	public List<CandidatoDocumento> getAll() throws NirhoServiceException{
		try {
			return candidatoDocumentoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	public List<CandidatoDocumento> getAllByCandidato(long idCandidato) throws NirhoServiceException{
		try {
			return candidatoDocumentoDAO.findByCandidato(idCandidato);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
