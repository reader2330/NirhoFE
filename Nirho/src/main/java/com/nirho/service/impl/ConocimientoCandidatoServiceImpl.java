package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ConocimientoCandidatoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConocimientoCandidato;
import com.nirho.service.ConocimientoCandidatoService;

@Service
public class ConocimientoCandidatoServiceImpl implements ConocimientoCandidatoService {
	
	public final static Logger logger = Logger.getLogger(ConocimientoCandidatoServiceImpl.class);
	
	@Autowired
	private ConocimientoCandidatoDAO conocimientoCandidatoDAO;
	
	public void editar(ConocimientoCandidato entidad) throws NirhoServiceException{
		try {
			conocimientoCandidatoDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ConocimientoCandidato entidad = conocimientoCandidatoDAO.getOne(id);
			if(entidad != null) {
				conocimientoCandidatoDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ConocimientoCandidato getOne(Long id) throws NirhoServiceException{
		try {
			return conocimientoCandidatoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ConocimientoCandidato> getAll() throws NirhoServiceException{
		try {
			return conocimientoCandidatoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
