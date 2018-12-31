package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ActividadesPuestoVacante;

public interface ActividadesPuestoVacanteService {
	void editar(ActividadesPuestoVacante e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public ActividadesPuestoVacante getOne(Long id) throws NirhoServiceException;
	public List<ActividadesPuestoVacante> getAll() throws NirhoServiceException;
}
