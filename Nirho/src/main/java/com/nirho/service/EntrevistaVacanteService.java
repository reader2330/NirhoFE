package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EntrevistaVacante;

public interface EntrevistaVacanteService {
	public void save(EntrevistaVacante e) throws NirhoServiceException;
	public void save(List<EntrevistaVacante> e) throws NirhoServiceException;
	public void eliminar(Long id) throws NirhoServiceException;
	public EntrevistaVacante getOne(Long id) throws NirhoServiceException;
	public List<EntrevistaVacante> getAll() throws NirhoServiceException;
}
