package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CaracteristicasCandidatoVacante;

public interface CaracteristicasCandidatoVacanteService {
	void editar(CaracteristicasCandidatoVacante e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public CaracteristicasCandidatoVacante getOne(Long id) throws NirhoServiceException;
	public List<CaracteristicasCandidatoVacante> getAll() throws NirhoServiceException;
}
