package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipantePVC;

public interface ParticipantePVCService {
	List<ParticipantePVC> getByProyecto(Integer idProyecto) throws NirhoServiceException;
	void save(List<ParticipantePVC> participantes) throws NirhoServiceException;
	public ParticipantePVC getOne(Integer id) throws NirhoServiceException;
	public List<ParticipantePVC> getAll(Integer idProyecto) throws NirhoServiceException;
}
