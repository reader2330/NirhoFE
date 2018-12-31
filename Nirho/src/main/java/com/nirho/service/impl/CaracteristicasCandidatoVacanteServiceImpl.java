package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CaracteristicasCandidatoVacanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CaracteristicasCandidatoVacante;
import com.nirho.service.CaracteristicasCandidatoVacanteService;

@Service
public class CaracteristicasCandidatoVacanteServiceImpl implements CaracteristicasCandidatoVacanteService {
	
	public final static Logger logger = Logger.getLogger(CaracteristicasCandidatoVacanteServiceImpl.class);
	
	@Autowired
	private CaracteristicasCandidatoVacanteDAO caracteristicasCandidatoVacanteDAO;
	
	public void editar(CaracteristicasCandidatoVacante entidad) throws NirhoServiceException{
		try {
			caracteristicasCandidatoVacanteDAO.update(entidad);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public void eliminar(Long id) throws NirhoServiceException{
		try {
			CaracteristicasCandidatoVacante entidad = caracteristicasCandidatoVacanteDAO.getOne(id);
			if(entidad != null) {
				caracteristicasCandidatoVacanteDAO.delete(entidad);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public CaracteristicasCandidatoVacante getOne(Long id) throws NirhoServiceException{
		try {
			return caracteristicasCandidatoVacanteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CaracteristicasCandidatoVacante> getAll() throws NirhoServiceException{
		try {
			return caracteristicasCandidatoVacanteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
	}
	
	
}
