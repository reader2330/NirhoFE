package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.EmpleadoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empleado;
import com.nirho.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	public final static Logger logger = Logger.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
	 
	@Override
	public void addEmpleado(Empleado p) throws NirhoServiceException {
		try {
			empleadoDAO.save(p);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void updateEmpleado(Empleado p) throws NirhoServiceException {
		try {
			empleadoDAO.update(p);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public List<Empleado> listEmpleados() throws NirhoServiceException {
		List<Empleado> empleadoList = null;
		try {
			empleadoList = empleadoDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}		
		return empleadoList;
	}
	
	@Override
	public Empleado getEmpleadoById(long id) throws NirhoServiceException {
		try {
			return empleadoDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	
	@Override
	public void removeEmpleado(Empleado empleado) throws NirhoServiceException {
		try {
			empleadoDAO.delete(empleado);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los empleados, causa [" + e.getMessage()+ "]");
		}	
	}
	

}
