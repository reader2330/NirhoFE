package com.nirho.service;

import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.exception.NirhoServiceException;

public interface CuestionarioProyectoService {
	void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException;
}
