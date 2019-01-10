package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ContratacionVacante;

public interface ContratacionVacanteService {
	public void save(ContratacionVacante e) throws NirhoServiceException;
	public void save(List<ContratacionVacante> e) throws NirhoServiceException;
	public void eliminar(Long id) throws NirhoServiceException;
	public ContratacionVacante getOne(Long id) throws NirhoServiceException;
	public List<ContratacionVacante> getByIdVacante(long id) throws NirhoServiceException;
	public List<ContratacionVacante> getByIdCandidato(long id) throws NirhoServiceException;
	public List<ContratacionVacante> getAll() throws NirhoServiceException;
}
