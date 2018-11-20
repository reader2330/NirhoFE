package com.nirho.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.EstatusProyectoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EstatusProyecto;
import com.nirho.service.EstatusProyectoService;

@Service
public class EstatusProyectoServiceImpl implements EstatusProyectoService {
	public final static Logger logger = Logger.getLogger(EstatusProyectoServiceImpl.class);
	
	@Autowired
	private EstatusProyectoDAO dao;

	@Override
	public EstatusProyecto obtenerEstatus(Integer idEstatusProyecto) throws NirhoServiceException {
		return dao.getOne(idEstatusProyecto);
	}

	
}
