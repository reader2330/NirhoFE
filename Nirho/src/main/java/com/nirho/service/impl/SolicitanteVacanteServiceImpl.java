package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.SolicitanteVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.SolicitanteVacante;
import com.nirho.service.SolicitanteVacanteService;

@Service
public class SolicitanteVacanteServiceImpl implements SolicitanteVacanteService {
	
	public final static Logger logger = Logger.getLogger(SolicitanteVacanteServiceImpl.class);
	
	@Autowired
	private SolicitanteVacanteDAO solicitanteVacanteDAO;
	
	public void editar(SolicitanteVacante entidad) throws NirhoServiceException{
		try {
			solicitanteVacanteDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			SolicitanteVacante entidad = solicitanteVacanteDAO.getOne(id);
			if(entidad != null) {
				solicitanteVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public SolicitanteVacante getOne(Long id) throws NirhoServiceException{
		try {
			return solicitanteVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<SolicitanteVacante> getAll() throws NirhoServiceException{
		try {
			return solicitanteVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
