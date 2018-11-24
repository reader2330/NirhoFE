package com.nirho.service;

import java.util.List;

import com.nirho.dto.GraficaProyectoDTO;
import com.nirho.dto.GraficaRespPregDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.GraficaProyecto;

public interface GraficasProyectoService {
	GraficaProyectoDTO obtenerGraficasProyecto(Integer idProyecto) throws NirhoServiceException;
	GraficaRespPregDTO obtenerGraficasRespuestasPreguntas(Integer idProyecto) throws NirhoServiceException;
	List<GraficaProyecto> obtenerGraficasProyectoPorAreaOrg(Integer idProyecto, String areaOrg) throws NirhoServiceException;
	void generarGraficasProyecto(Integer idProyecto) throws NirhoServiceException;
	void persistirGraficaProyecto(List<GraficaProyecto> graficas) throws NirhoServiceException;
	void guardarComentario(GraficaProyecto graficaProyecto) throws NirhoServiceException;
}
