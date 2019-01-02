package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.IdiomaCandidato;

public interface IdiomaCandidatoService {
	void editar(IdiomaCandidato e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public IdiomaCandidato getOne(Long id) throws NirhoServiceException;
	public List<IdiomaCandidato> getAll() throws NirhoServiceException;
}
