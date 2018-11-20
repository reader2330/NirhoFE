package com.nirho.service;

import com.nirho.dto.CuestionarioConfiguracion;
import com.nirho.dto.VerTemaQ;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuetionarioParticipante;

import java.util.List;

public interface CuestionarioParticipanteService {
	void guardar(CuetionarioParticipante cuetionarioParticipante) throws NirhoServiceException;
}
