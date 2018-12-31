package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Solicitante;

public interface SolicitanteService {
	
	List<Solicitante> getByRFC(String rfc) throws NirhoServiceException;
	void save(Solicitante solicitante) throws NirhoServiceException;
	void save(List<Solicitante> solicitantes) throws NirhoServiceException;
	public Solicitante getOne(Integer id) throws NirhoServiceException;
	public List<Solicitante> getAll() throws NirhoServiceException;
	
}
