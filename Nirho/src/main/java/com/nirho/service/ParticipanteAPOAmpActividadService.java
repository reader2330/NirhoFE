package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPOAmpActividad;

public interface ParticipanteAPOAmpActividadService {
	public ParticipanteAPOAmpActividad getOne(Integer id) throws NirhoServiceException;
	public void guardar(List<ParticipanteAPOAmpActividad> actividades) throws NirhoServiceException;
	public void guardar(ParticipanteAPOAmpActividad actividad) throws NirhoServiceException;
}
