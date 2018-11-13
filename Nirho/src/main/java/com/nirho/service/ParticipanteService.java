package com.nirho.service;

import java.util.List;

import com.nirho.dto.NivelDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Participante;

public interface ParticipanteService {
	List<NivelDTO> obtenerParticipantesPorProyecto(Integer idProyecto) throws NirhoServiceException;
	void guardarParticipanteService(List<Participante> participantes) throws NirhoServiceException;
	void ampliarParticipanteService(List<Participante> participantes) throws NirhoServiceException;
	List<Participante> obtenerParticipantes(Integer idProyecto) throws NirhoServiceException;
}
