package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ActividadesPuestoVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.service.ActividadesPuestoVacanteService;

@Service
public class ActividadesPuestoVacanteServiceImpl implements ActividadesPuestoVacanteService {
	
	public final static Logger logger = Logger.getLogger(ActividadesPuestoVacanteServiceImpl.class);
	
	@Autowired
	private ActividadesPuestoVacanteDAO actividadesPuestoVacanteDAO;
	
	public void editar(ActividadesPuestoVacante entidad) throws NirhoServiceException{
		try {
			actividadesPuestoVacanteDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			ActividadesPuestoVacante entidad = actividadesPuestoVacanteDAO.getOne(id);
			if(entidad != null) {
				actividadesPuestoVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public ActividadesPuestoVacante getOne(Long id) throws NirhoServiceException{
		try {
			return actividadesPuestoVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<ActividadesPuestoVacante> getAll() throws NirhoServiceException{
		try {
			return actividadesPuestoVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
