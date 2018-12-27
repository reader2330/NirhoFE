package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.dao.OpcionDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.Opcion;
import com.nirho.service.CuestionarioParticipanteService;

@Service
public class CuestionarioParticipanteServiceImpl implements CuestionarioParticipanteService {

	public final static Logger logger = Logger.getLogger(CuestionarioParticipanteServiceImpl.class);

	@Autowired
	private CuestionarioParticipanteDAO dao;
	@Autowired
	private OpcionDAO opcionDAO;
	
	@Override
	public void guardar(CuetionarioParticipante cuestionario) throws NirhoServiceException {
		try {
			dao.save(cuestionario);
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al registrar el cuestionario del participante en la BD");
		}
	}

	@Override
	public List<CuetionarioParticipante> obtenerCuestionarioParticipante(Integer idParticipante, Integer idProyecto)  throws NirhoServiceException {
		try {
			return dao.findByParticipanteProyecto(idParticipante, idProyecto);
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener el cuestionario del participante en la BD");
		}
	}

	@Override
	public List<Opcion> opcionesTema(Integer idTema) throws NirhoServiceException {
		try {
			return opcionDAO.findByIdTema(idTema);
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas al obtener las opciones del tema en la BD");
		}
	}

}

