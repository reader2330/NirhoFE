package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CandidatoDocumento;

public interface CandidatoDocumentoService {
	public void save(CandidatoDocumento e) throws NirhoServiceException;
	public void save(List<CandidatoDocumento> e) throws NirhoServiceException;
	public void eliminar(long id) throws NirhoServiceException;
	public CandidatoDocumento getOne(long id) throws NirhoServiceException;
	public List<CandidatoDocumento> getAll() throws NirhoServiceException;
	public List<CandidatoDocumento> getAllByCandidato(long id) throws NirhoServiceException;
}
