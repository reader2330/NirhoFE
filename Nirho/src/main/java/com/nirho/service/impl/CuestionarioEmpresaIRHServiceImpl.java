package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioEmpresaIRHDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRH;
import com.nirho.service.CuestionarioEmpresaIRHService;
 
@Service
public class CuestionarioEmpresaIRHServiceImpl implements CuestionarioEmpresaIRHService {
	 
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaIRHServiceImpl.class);
	
	@Autowired
	private CuestionarioEmpresaIRHDAO cuestionarioEmpresaIRHDAO;
	
	public void addCuestionarioEmpresaIRH(CuestionarioEmpresaIRH ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHDAO.save(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public void updateCuestionarioEmpresaIRH(CuestionarioEmpresaIRH ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHDAO.update(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public CuestionarioEmpresaIRH getCuestionarioEmpresaIRHById(long id) throws NirhoServiceException {
		try {
			return cuestionarioEmpresaIRHDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CuestionarioEmpresaIRH> listCuestionarioEmpresaIRH() throws NirhoServiceException{
		List<CuestionarioEmpresaIRH> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioEmpresaIRHDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	
	public List<CuestionarioEmpresaIRH> getListCuestionarioEmpresaIRHByEmpresaId(long id) throws NirhoServiceException{
		List<CuestionarioEmpresaIRH> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioEmpresaIRHDAO.findByIdEmpresa(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	
	@Override
	public void removeCuestionarioEmpresaIRH(CuestionarioEmpresaIRH cuestionarioEmpresaIRH) throws NirhoServiceException {
		try {
			cuestionarioEmpresaIRHDAO.delete(cuestionarioEmpresaIRH);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
}
