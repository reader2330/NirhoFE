package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CompetenciasVacante;

public interface CompetenciasVacanteService {
	void editar(CompetenciasVacante e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public CompetenciasVacante getOne(Long id) throws NirhoServiceException;
	public List<CompetenciasVacante> getAll() throws NirhoServiceException;
}
