package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ContactoCandidato;

public interface ContactoCandidatoService {
	void editar(ContactoCandidato e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public ContactoCandidato getOne(Long id) throws NirhoServiceException;
	public List<ContactoCandidato> getAll() throws NirhoServiceException;
}
