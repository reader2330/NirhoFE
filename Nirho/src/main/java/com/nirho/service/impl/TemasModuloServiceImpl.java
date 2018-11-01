package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ModuloDAO;
import com.nirho.dao.PreguntaTemaDAO;
import com.nirho.dto.TemaPreguntas;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.PreguntaTema;
import com.nirho.model.TemaCuestionario;
import com.nirho.service.TemasModuloService;

@Service
public class TemasModuloServiceImpl implements TemasModuloService {
	public final static Logger logger = Logger.getLogger(TemasModuloServiceImpl.class);
	
	@Autowired
	private ModuloDAO dao;
	@Autowired
	PreguntaTemaDAO preguntaDAO;

	@Override
	public List<TemaCuestionario> obtenerTemasCuestionario(Integer idModulo) throws NirhoServiceException {
		List<TemaCuestionario> temas = new ArrayList<>();
		try {
			temas = dao.getOne(idModulo).getTemaCuestionarioList();
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas con la BD al obtener los temas del modulo [" + idModulo + "]");
		}
		return temas;
	}

	@Override
	public List<PreguntaTema> obtenerPreguntasTema(Integer idTema) throws NirhoServiceException {
		List<PreguntaTema> preguntas = new ArrayList<>();
		try {
			preguntas = preguntaDAO.findByIdTema(idTema);
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas con la BD al obtener las preguntas del tema [" + idTema + "]");
		}
		return preguntas;
	}

	@Override
	public List<TemaPreguntas> obtenerPlantillaCuestionario(Integer idModulo) throws NirhoServiceException {
		List<TemaPreguntas> temas = new ArrayList<>();
		try {
			for(TemaCuestionario tema: dao.getOne(idModulo).getTemaCuestionarioList()) {
				TemaPreguntas temaP = new TemaPreguntas();
				temaP.setTema(tema);
				temaP.setPreguntas(preguntaDAO.findByIdTema(tema.getIdTema()));
				temas.add(temaP);
			}
		} catch(Exception e) {
			logger.info("Exception e [" + e.getMessage() +"]");
			throw new NirhoServiceException("Problemas con la BD al obtener los temas del modulo [" + idModulo + "]");
		}
		return temas;
	}
	
}
