package com.nirho.service;

import java.util.List;

import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;

public interface CuestionarioProyectoService {
	void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException;
	List<VerTemaQ> verTemasCuestionario(Integer idProyecto) throws NirhoServiceException;
}
