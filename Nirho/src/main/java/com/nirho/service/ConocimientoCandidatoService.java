package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConocimientoCandidato;

public interface ConocimientoCandidatoService {
	void editar(ConocimientoCandidato e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public ConocimientoCandidato getOne(Long id) throws NirhoServiceException;
	public List<ConocimientoCandidato> getAll() throws NirhoServiceException;
}
