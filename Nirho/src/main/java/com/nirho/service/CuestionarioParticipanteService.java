package com.nirho.service;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuetionarioParticipante;

public interface CuestionarioParticipanteService {
	void guardar(CuetionarioParticipante cuetionarioParticipante) throws NirhoServiceException;
}
