package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioEmpresaIRHTemaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRHTema;
import com.nirho.service.CuestionarioEmpresaIRHTemaService;
 
@Service
public class CuestionarioEmpresaIRHTemaServiceImpl implements CuestionarioEmpresaIRHTemaService {
	 
	public final static Logger logger = Logger.getLogger(CuestionarioEmpresaIRHTemaServiceImpl.class);
	
	@Autowired
	private CuestionarioEmpresaIRHTemaDAO cuestionarioEmpresaIRHTemaDAO;
	
	public void addCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHTemaDAO.save(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public void updateCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema ce) throws NirhoServiceException{
		try {
			cuestionarioEmpresaIRHTemaDAO.update(ce);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public CuestionarioEmpresaIRHTema getCuestionarioEmpresaIRHTemaById(long id) throws NirhoServiceException {
		try {
			return cuestionarioEmpresaIRHTemaDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
	public List<CuestionarioEmpresaIRHTema> listCuestionarioEmpresaIRHTema() throws NirhoServiceException{
		List<CuestionarioEmpresaIRHTema> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioEmpresaIRHTemaDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	
	public List<CuestionarioEmpresaIRHTema> getListCuestionarioEmpresaIRHTemaByEmpresaId(long id) throws NirhoServiceException{
		List<CuestionarioEmpresaIRHTema> cuestionarioEmpresaList = null;
		try {
			cuestionarioEmpresaList = cuestionarioEmpresaIRHTemaDAO.findByIdEmpresa(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return cuestionarioEmpresaList;
	}
	
	@Override
	public void removeCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema cuestionarioEmpresaIRHTema) throws NirhoServiceException {
		try {
			cuestionarioEmpresaIRHTemaDAO.delete(cuestionarioEmpresaIRHTema);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
}
