package com.nirho.service;

import java.util.List;

import com.nirho.dto.GraficaAreaOrgDTO;
import com.nirho.exception.NirhoServiceException;

public interface GraficasProyectoService {
	List<GraficaAreaOrgDTO> obtenerGraficasProyecto(Integer idProyecto) throws NirhoServiceException;
}
