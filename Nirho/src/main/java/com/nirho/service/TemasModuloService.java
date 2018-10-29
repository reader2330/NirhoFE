package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.PreguntaTema;
import com.nirho.model.TemaCuestionario;

public interface TemasModuloService {
	List<TemaCuestionario> obtenerTemasCuestionario(Integer idModulo) throws NirhoServiceException;
	List<PreguntaTema> obtenerPreguntasTema(Integer idTema) throws NirhoServiceException;
}
