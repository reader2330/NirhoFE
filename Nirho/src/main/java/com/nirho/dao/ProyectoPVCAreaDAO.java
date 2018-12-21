package com.nirho.dao;

import java.util.List;

import com.nirho.model.ProyectoPVCArea;

public interface ProyectoPVCAreaDAO extends BaseDAO<ProyectoPVCArea, Integer> {
	public List<ProyectoPVCArea> findByIdProyecto(Integer idProyecto);
}
