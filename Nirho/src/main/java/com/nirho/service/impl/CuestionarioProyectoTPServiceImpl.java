package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioProyectoTPDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyectoTP;
import com.nirho.service.CuestionarioProyectoTPService;
 
@Service
public class CuestionarioProyectoTPServiceImpl implements CuestionarioProyectoTPService {
	 
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoTPServiceImpl.class);
	
	@Autowired
	private CuestionarioProyectoTPDAO cuestionarioProyectoTPDAO;
	
	public void addCuestionarioProyectoTP(CuestionarioProyectoTP ce) throws NirhoServiceException{
		try {
			cuestionarioProyectoTPDAO.save(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public void updateCuestionarioProyectoTP(CuestionarioProyectoTP ce) throws NirhoServiceException{
		try {
			cuestionarioProyectoTPDAO.update(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public CuestionarioProyectoTP getCuestionarioProyectoTPById(long id) throws NirhoServiceException {
		try {
			return cuestionarioProyectoTPDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CuestionarioProyectoTP> listCuestionarioProyectoTP() throws NirhoServiceException{
		List<CuestionarioProyectoTP> cuestionarioProyectoList = null;
		try {
			cuestionarioProyectoList = cuestionarioProyectoTPDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioProyectoList;
	}
	
	public List<CuestionarioProyectoTP> getListCuestionarioProyectoTPByProyectoId(long id) throws NirhoServiceException{
		List<CuestionarioProyectoTP> cuestionarioProyectoList = null;
		try {
			cuestionarioProyectoList = cuestionarioProyectoTPDAO.findByIdProyecto(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioProyectoList;
	}
	 
	@Override
	public void removeCuestionarioProyectoTP(CuestionarioProyectoTP cuestionarioProyectoTP) throws NirhoServiceException {
		try {
			cuestionarioProyectoTPDAO.delete(cuestionarioProyectoTP);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}	
	}
	
}
