package com.nirho.service;

import com.nirho.exception.NirhoServiceException;
import com.nirho.dto.GraficasProyectoDTO;

public interface GraficasProyectoService {
	GraficasProyectoDTO obtenerGraficasProyecto(Integer idProyecto) throws NirhoServiceException;
}
