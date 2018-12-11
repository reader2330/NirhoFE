package com.nirho.service;

import java.util.List;

import com.nirho.dto.TemaPreguntas;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Opcion;
import com.nirho.model.Pregunta;
import com.nirho.model.Tema;

public interface TemasModuloService {
	List<Tema> obtenerTemasCuestionario(Integer idModulo) throws NirhoServiceException;
	List<Pregunta> obtenerPreguntasTema(Integer idTema) throws NirhoServiceException;
	List<TemaPreguntas> obtenerPlantillaCuestionario(Integer idModulo) throws NirhoServiceException;
	List<Opcion> obtenerOpcionesTema(Integer idTema) throws NirhoServiceException;
}
