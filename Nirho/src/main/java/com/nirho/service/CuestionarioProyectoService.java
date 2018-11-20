package com.nirho.service;

import java.util.List;

import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuetionarioParticipante;

public interface CuestionarioProyectoService {
	void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException;
	List<VerTemaQ> verTemasCuestionario(Integer idProyecto) throws NirhoServiceException;
	List<CuetionarioParticipante> obtenerCuestionarioParticipante(String token) throws NirhoServiceException;
	void contestarPregunta(CuetionarioParticipante questPart) throws NirhoServiceException;
	List<CuestionarioProyecto> obtenerCuestionarioProyecto(Integer idProyecto) throws NirhoServiceException;
}
