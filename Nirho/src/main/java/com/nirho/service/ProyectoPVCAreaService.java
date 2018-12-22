package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCArea;

public interface ProyectoPVCAreaService {
	public ProyectoPVCArea getOne(Integer id) throws NirhoServiceException;
	public void guardar(ProyectoPVCArea e) throws NirhoServiceException;
	public void guardar(List<ProyectoPVCArea> list) throws NirhoServiceException;
	public List<ProyectoPVCArea> getByProyecto(Integer idProyecto) throws NirhoServiceException;
}
