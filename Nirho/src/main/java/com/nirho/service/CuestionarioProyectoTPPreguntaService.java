package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyectoTPPregunta;

public interface CuestionarioProyectoTPPreguntaService {
	 
	public void addCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta e) throws NirhoServiceException;
	public void updateCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta e) throws NirhoServiceException;
	public CuestionarioProyectoTPPregunta getCuestionarioProyectoTPPreguntaById(long id) throws NirhoServiceException;
	public List<CuestionarioProyectoTPPregunta> listCuestionarioProyectoTPPregunta() throws NirhoServiceException;
	public void removeCuestionarioProyectoTPPregunta(CuestionarioProyectoTPPregunta cuestionarioProyectoTPPregunta) throws NirhoServiceException;
	
}
