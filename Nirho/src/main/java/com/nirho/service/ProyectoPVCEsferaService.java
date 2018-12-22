package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCEsfera;

public interface ProyectoPVCEsferaService {
	public ProyectoPVCEsfera getOne(Integer id) throws NirhoServiceException;
	public void guardar(ProyectoPVCEsfera e) throws NirhoServiceException;
	public void guardar(List<ProyectoPVCEsfera> list) throws NirhoServiceException;
}
