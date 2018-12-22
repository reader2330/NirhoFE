package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCConocimiento;

public interface ProyectoPVCConocimientoService {
	public ProyectoPVCConocimiento getOne(Integer id) throws NirhoServiceException;
	public void guardar(ProyectoPVCConocimiento e) throws NirhoServiceException;
	public void guardar(List<ProyectoPVCConocimiento> list) throws NirhoServiceException;
} 
