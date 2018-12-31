package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CompetenciasVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CompetenciasVacante;
import com.nirho.service.CompetenciasVacanteService;

@Service
public class CompetenciasVacanteServiceImpl implements CompetenciasVacanteService {
	
	public final static Logger logger = Logger.getLogger(CompetenciasVacanteServiceImpl.class);
	
	@Autowired
	private CompetenciasVacanteDAO competenciasVacanteDAO;
	
	public void editar(CompetenciasVacante entidad) throws NirhoServiceException{
		try {
			competenciasVacanteDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			CompetenciasVacante entidad = competenciasVacanteDAO.getOne(id);
			if(entidad != null) {
				competenciasVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public CompetenciasVacante getOne(Long id) throws NirhoServiceException{
		try {
			return competenciasVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CompetenciasVacante> getAll() throws NirhoServiceException{
		try {
			return competenciasVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
