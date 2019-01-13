package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.SolicitanteVacante;

public interface SolicitanteVacanteService {
	void editar(SolicitanteVacante e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public SolicitanteVacante getOne(Long id) throws NirhoServiceException;
	public List<SolicitanteVacante> getAll() throws NirhoServiceException;
	public List<SolicitanteVacante> getByIdConsultor(long idConsultor) throws NirhoServiceException;
}
