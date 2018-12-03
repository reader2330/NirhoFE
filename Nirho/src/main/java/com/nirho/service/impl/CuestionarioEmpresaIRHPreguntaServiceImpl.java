package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioEmpresaIRHPreguntaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRHPregunta;
import com.nirho.service.CuestionarioEmpresaIRHPreguntaService;
 
@Service
public class CuestionarioEmpresaIRHPreguntaServiceImpl implements CuestionarioEmpresaIRHPreguntaService {
	 
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaIRHPreguntaServiceImpl.class);
	
	@Autowired
	private CuestionarioEmpresaIRHPreguntaDAO cuestionarioEmpresaIRHPreguntaDAO;
	
	public void addCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHPreguntaDAO.save(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public void updateCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHPreguntaDAO.update(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public CuestionarioEmpresaIRHPregunta getCuestionarioEmpresaIRHPreguntaById(long id) throws NirhoServiceException {
		try {
			return cuestionarioEmpresaIRHPreguntaDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CuestionarioEmpresaIRHPregunta> listCuestionarioEmpresaIRHPregunta() throws NirhoServiceException{
		List<CuestionarioEmpresaIRHPregunta> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioEmpresaIRHPreguntaDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	
	@Override
	public void removeCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta CuestionarioEmpresaIRHPregunta) throws NirhoServiceException {
		try {
			cuestionarioEmpresaIRHPreguntaDAO.delete(CuestionarioEmpresaIRHPregunta);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
}
