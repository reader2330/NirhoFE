package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.Opcion;

public interface CuestionarioParticipanteService {
	void guardar(CuetionarioParticipante cuestionario) throws NirhoServiceException;
	List<CuetionarioParticipante> obtenerCuestionarioParticipante(Integer idParticipante,Integer idProyecto) throws NirhoServiceException;
	List<Opcion> opcionesTema(Integer idTema) throws NirhoServiceException;
}
