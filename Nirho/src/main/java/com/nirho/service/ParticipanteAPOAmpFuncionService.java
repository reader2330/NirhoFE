package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPOAmpFuncion;

public interface ParticipanteAPOAmpFuncionService {
	public ParticipanteAPOAmpFuncion getOne(Integer id) throws NirhoServiceException;
	public void guardar(List<ParticipanteAPOAmpFuncion> funciones) throws NirhoServiceException;
	public void guardar(ParticipanteAPOAmpFuncion funcion) throws NirhoServiceException;
	public List<ParticipanteAPOAmpFuncion> list() throws NirhoServiceException;
}
