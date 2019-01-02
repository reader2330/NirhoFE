package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ExperienciaCandidato;

public interface ExperienciaCandidatoService {
	void editar(ExperienciaCandidato e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public ExperienciaCandidato getOne(Long id) throws NirhoServiceException;
	public List<ExperienciaCandidato> getAll() throws NirhoServiceException;
}
