package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioProyectoTP;

public abstract interface CuestionarioProyectoTPDAO extends BaseDAO<CuestionarioProyectoTP, Long> {

	public List<CuestionarioProyectoTP> findByIdProyecto(Long idProyecto);
	 
}
