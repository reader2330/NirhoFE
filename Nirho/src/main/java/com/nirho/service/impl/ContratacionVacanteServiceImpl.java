package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ContratacionVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ContratacionVacante;
import com.nirho.service.ContratacionVacanteService;

@Service
public class ContratacionVacanteServiceImpl implements ContratacionVacanteService {
	  
	public final static Logger logger = Logger.getLogger(ContratacionVacanteServiceImpl.class);
	
	@Autowired
	private ContratacionVacanteDAO contratacionVacanteDAO;
	
	public void save(ContratacionVacante entidad) throws NirhoServiceException{
		try {
			
			ContratacionVacante s = contratacionVacanteDAO.getOne(entidad.getId());
			if(s == null) {
				contratacionVacanteDAO.save(entidad);
			} else {
				contratacionVacanteDAO.update(entidad);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void save(List<ContratacionVacante> l) throws NirhoServiceException{
		try {
			for(ContratacionVacante entidad: l) {
				ContratacionVacante s = contratacionVacanteDAO.getOne(entidad.getId());
				if(s == null) {
					contratacionVacanteDAO.save(entidad);
				} else {
					contratacionVacanteDAO.update(entidad);
				}
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ContratacionVacante entidad = contratacionVacanteDAO.getOne(id);
			if(entidad != null) {
				contratacionVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ContratacionVacante getOne(Long id) throws NirhoServiceException{
		try {
			return contratacionVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ContratacionVacante> getAll() throws NirhoServiceException{
		try {
			return contratacionVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	public List<ContratacionVacante> getByIdVacante(long id) throws NirhoServiceException{
		try {
			return contratacionVacanteDAO.findByIdVacante(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public List<ContratacionVacante> getByIdCandidato(long id) throws NirhoServiceException{
		try {
			return contratacionVacanteDAO.findByIdCandidato(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
}
