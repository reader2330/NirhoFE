package com.nirho.service;

import java.util.List;

import com.nirho.dto.CuestionarioConfEVD;
import com.nirho.dto.CuestionarioConfOpcion;
import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.PreguntaOpcionesEVD;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioOpcion;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.Tema;

public interface CuestionarioProyectoService {
	void guardar(CuestionarioConfiguracion cuestionario) throws NirhoServiceException;
	void guardar(CuestionarioConfEVD cuestionario) throws NirhoServiceException;
	List<VerTemaQ> verTemasCuestionario(Integer idProyecto) throws NirhoServiceException;
	List<CuetionarioParticipante> obtenerCuestionarioParticipante(String token) throws NirhoServiceException;
	void contestarPregunta(CuetionarioParticipante questPart) throws NirhoServiceException;
	List<CuestionarioProyecto> obtenerCuestionarioProyecto(Integer idProyecto) throws NirhoServiceException;
	void guardarOpciones(CuestionarioConfOpcion cuestionario) throws NirhoServiceException;
	List<CuestionarioOpcion> obtenerCuestionarioOpcion(Integer idProyecto) throws NirhoServiceException;
	List<Tema> obtenerTemasProyecto(Integer idProyecto) throws NirhoServiceException;
	List<PreguntaOpcionesEVD> obtenerPreguntasOpciones(Integer idProyecto) throws NirhoServiceException;
}
