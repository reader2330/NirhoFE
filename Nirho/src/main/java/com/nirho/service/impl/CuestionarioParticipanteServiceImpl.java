package com.nirho.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.service.CuestionarioParticipanteService;

@Service
public class CuestionarioParticipanteServiceImpl implements CuestionarioParticipanteService {

	public final static Logger logger = Logger.getLogger(CuestionarioParticipanteServiceImpl.class);

	@Autowired
	private CuestionarioParticipanteDAO dao;

	@Override
	public void guardar(CuetionarioParticipante cuestionario) throws NirhoServiceException {
		dao.save(cuestionario);
	}

}

