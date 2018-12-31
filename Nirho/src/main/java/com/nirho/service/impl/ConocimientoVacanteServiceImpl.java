package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ConocimientoVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConocimientoVacante;
import com.nirho.service.ConocimientoVacanteService;

@Service
public class ConocimientoVacanteServiceImpl implements ConocimientoVacanteService {
	
	public final static Logger logger = Logger.getLogger(ConocimientoVacanteServiceImpl.class);
	
	@Autowired
	private ConocimientoVacanteDAO conocimientoVacanteDAO;
	
	public void editar(ConocimientoVacante entidad) throws NirhoServiceException{
		try {
			conocimientoVacanteDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ConocimientoVacante entidad = conocimientoVacanteDAO.getOne(id);
			if(entidad != null) {
				conocimientoVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ConocimientoVacante getOne(Long id) throws NirhoServiceException{
		try {
			return conocimientoVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ConocimientoVacante> getAll() throws NirhoServiceException{
		try {
			return conocimientoVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
