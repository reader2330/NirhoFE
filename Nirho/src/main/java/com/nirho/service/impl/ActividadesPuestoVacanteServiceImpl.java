package com.nirho.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.service.ActividadesPuestoVacanteService;

@Service
public class ActividadesPuestoVacanteServiceImpl implements ActividadesPuestoVacanteService {
	public final static Logger logger = Logger.getLogger(ActividadesPuestoVacanteServiceImpl.class);
	@Autowired
	private ActividadesPuestoVacanteService dao;
	
	public ActividadesPuestoVacanteService getDao() {
		return dao;
	}
	
}
