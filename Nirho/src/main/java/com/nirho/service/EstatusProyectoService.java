package com.nirho.service;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EstatusProyecto;

public interface EstatusProyectoService {
	EstatusProyecto obtenerEstatus(Integer idEstatusProyecto) throws NirhoServiceException;
}
