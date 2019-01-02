package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CaracteristicasCandidatoCv;

public interface CaracteristicasCandidatoCvService {
	void editar(CaracteristicasCandidatoCv e) throws NirhoServiceException;
	void eliminar(Long id) throws NirhoServiceException;
	public CaracteristicasCandidatoCv getOne(Long id) throws NirhoServiceException;
	public List<CaracteristicasCandidatoCv> getAll() throws NirhoServiceException;
}
