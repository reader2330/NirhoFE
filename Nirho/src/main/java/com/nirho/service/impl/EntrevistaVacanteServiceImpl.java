package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.EntrevistaVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EntrevistaVacante;
import com.nirho.service.EntrevistaVacanteService;

@Service
public class EntrevistaVacanteServiceImpl implements EntrevistaVacanteService {
	  
	public final static Logger logger = Logger.getLogger(EntrevistaVacanteServiceImpl.class);
	
	@Autowired
	private EntrevistaVacanteDAO entrevistaVacanteDAO;
	
	public void save(EntrevistaVacante entidad) throws NirhoServiceException{
		try {
			
			EntrevistaVacante s = entrevistaVacanteDAO.getOne(entidad.getId());
			if(s == null) {
				entrevistaVacanteDAO.save(entidad);
			} else {
				entrevistaVacanteDAO.update(entidad);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void save(List<EntrevistaVacante> l) throws NirhoServiceException{
		try {
			for(EntrevistaVacante entidad: l) {
				EntrevistaVacante s = entrevistaVacanteDAO.getOne(entidad.getId());
				if(s == null) {
					entrevistaVacanteDAO.save(entidad);
				} else {
					entrevistaVacanteDAO.update(entidad);
				}
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			EntrevistaVacante entidad = entrevistaVacanteDAO.getOne(id);
			if(entidad != null) {
				entrevistaVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public EntrevistaVacante getOne(Long id) throws NirhoServiceException{
		try {
			return entrevistaVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<EntrevistaVacante> getAll() throws NirhoServiceException{
		try {
			return entrevistaVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	public List<EntrevistaVacante> getByIdCandidato(long idCandidato) throws NirhoServiceException{
		try {
			return entrevistaVacanteDAO.findByIdCandidato(idCandidato);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	public List<EntrevistaVacante> getByIdConsultor(long idConsultor) throws NirhoServiceException{
		try {
			return entrevistaVacanteDAO.findByIdConsultor(idConsultor);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	public List<EntrevistaVacante> getByIdSolicitante(long idSolicitante) throws NirhoServiceException{
		try {
			return entrevistaVacanteDAO.findByIdSolicitante(idSolicitante);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
}
