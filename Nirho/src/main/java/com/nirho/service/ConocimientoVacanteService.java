package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConocimientoVacante;

public interface ConocimientoVacanteService {
	void editar(ConocimientoVacante e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public ConocimientoVacante getOne(Long id) throws NirhoServiceException;
	public List<ConocimientoVacante> getAll() throws NirhoServiceException;
}
