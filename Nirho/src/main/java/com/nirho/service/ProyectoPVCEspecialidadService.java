package com.nirho.service;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCEspecialidad;

public interface ProyectoPVCEspecialidadService {
	public ProyectoPVCEspecialidad getOne(Integer id) throws NirhoServiceException;
	public void guardar(ProyectoPVCEspecialidad e) throws NirhoServiceException;
}
