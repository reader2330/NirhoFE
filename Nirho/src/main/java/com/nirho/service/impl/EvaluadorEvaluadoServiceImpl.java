package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.EvaluadorEvaluadoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EvaluadorEvaluado;
import com.nirho.service.EvaluadorEvaluadoService;

@Service
public class EvaluadorEvaluadoServiceImpl implements EvaluadorEvaluadoService {
	public final static Logger logger = Logger.getLogger(EvaluadorEvaluadoServiceImpl.class);
	
	@Autowired
	private EvaluadorEvaluadoDAO dao;
	
	@Override
	public void guardar(EvaluadorEvaluado evaluador) throws NirhoServiceException {
		dao.save(evaluador);
	}
	
	@Override
	public List<EvaluadorEvaluado> obtenerEvaluados(Integer idProyecto) throws NirhoServiceException {
		List<EvaluadorEvaluado> evaluadores = new ArrayList<>();
		try {
			evaluadores = dao.findByIdProyecto(idProyecto);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return evaluadores;
	}
	
}
