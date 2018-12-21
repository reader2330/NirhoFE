package com.nirho.service;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCNivel;

public interface ProyectoPVCNivelService {
	public ProyectoPVCNivel getOne(Integer id) throws NirhoServiceException;
	public void guardar(ProyectoPVCNivel e) throws NirhoServiceException;
}
