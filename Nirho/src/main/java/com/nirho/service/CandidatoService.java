package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Candidato;

public interface CandidatoService {
	public void save(Candidato e) throws NirhoServiceException;
	public void save(List<Candidato> e) throws NirhoServiceException;
	public void eliminar(Long id) throws NirhoServiceException;
	public Candidato getOne(Long id) throws NirhoServiceException;
	public Candidato getOneByUsername(String username) throws NirhoServiceException;
	public Candidato getOneByRFC(String rfc) throws NirhoServiceException;
	public List<Candidato> getAll() throws NirhoServiceException;
}
