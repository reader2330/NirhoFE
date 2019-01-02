package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CaracteristicasCandidatoCvDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CaracteristicasCandidatoCv;
import com.nirho.service.CaracteristicasCandidatoCvService;

@Service
public class CaracteristicasCandidatoCvServiceImpl implements CaracteristicasCandidatoCvService {
	
	public final static Logger logger = Logger.getLogger(CaracteristicasCandidatoCvServiceImpl.class);
	
	@Autowired
	private CaracteristicasCandidatoCvDAO caracteristicasCandidatoCvDAO;
	
	public void editar(CaracteristicasCandidatoCv entidad) throws NirhoServiceException{
		try {
			caracteristicasCandidatoCvDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			CaracteristicasCandidatoCv entidad = caracteristicasCandidatoCvDAO.getOne(id);
			if(entidad != null) {
				caracteristicasCandidatoCvDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public CaracteristicasCandidatoCv getOne(Long id) throws NirhoServiceException{
		try {
			return caracteristicasCandidatoCvDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CaracteristicasCandidatoCv> getAll() throws NirhoServiceException{
		try {
			return caracteristicasCandidatoCvDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
