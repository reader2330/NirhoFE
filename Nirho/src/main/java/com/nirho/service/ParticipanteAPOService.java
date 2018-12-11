package com.nirho.service;

import java.util.List;
import java.util.Set;

import com.nirho.dto.NivelDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ParticipanteAPOAmp;

public interface ParticipanteAPOService {
	List<ParticipanteAPO> obtenerParticipantesPorProyecto(Integer idProyecto) throws NirhoServiceException;
	void guardarParticipanteService(List<ParticipanteAPO> participantes) throws NirhoServiceException;
	void ampliarParticipanteService(List<ParticipanteAPOAmp> participantes) throws NirhoServiceException;
	public ParticipanteAPO getOne(Integer id) throws NirhoServiceException;
	public List<ParticipanteAPO> obtenerParticipantes(Integer idProyecto) throws NirhoServiceException;
	public Set<ParticipanteAPOAmp> obtenerAmpliaciones(Integer idParticipante) throws NirhoServiceException;
}
