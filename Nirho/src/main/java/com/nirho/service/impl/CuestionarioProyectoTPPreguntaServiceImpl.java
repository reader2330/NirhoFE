package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioProyectoTPPreguntaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyectoTPPregunta;
import com.nirho.service.CuestionarioProyectoTPPreguntaService;
 
@Service
public class CuestionarioProyectoTPPreguntaServiceImpl implements CuestionarioProyectoTPPreguntaService {
	 
	public final static Logger logger = Logger.getLogger(CuestionarioProyectoTPPreguntaServiceImpl.class);
	
	@Autowired
	private CuestionarioProyectoTPPreguntaDAO cuestionarioProyectoTPPreguntaDAO;
	
	public void addCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta ce) throws NirhoServiceException{
		try {
			cuestionarioProyectoTPPreguntaDAO.save(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public void updateCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta ce) throws NirhoServiceException{
		try {
			cuestionarioProyectoTPPreguntaDAO.update(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public CuestionarioProyectoTPPregunta getCuestionarioProyectoTPPreguntaById(long id) throws NirhoServiceException {
		try {
			return cuestionarioProyectoTPPreguntaDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}	
	}
	 
	public List<CuestionarioProyectoTPPregunta> listCuestionarioProyectoTPPregunta() throws NirhoServiceException{
		List<CuestionarioProyectoTPPregunta> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioProyectoTPPreguntaDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	 
	@Override
	public void removeCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta CuestionarioProyectoTPPregunta) throws NirhoServiceException {
		try {
			cuestionarioProyectoTPPreguntaDAO.delete(CuestionarioProyectoTPPregunta);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}	
	}
	
}
