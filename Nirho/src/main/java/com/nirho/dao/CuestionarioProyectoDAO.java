package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuestionarioProyectoPK;

public interface CuestionarioProyectoDAO extends BaseDAO<CuestionarioProyecto, CuestionarioProyectoPK> {
	List<CuestionarioProyecto> findByIdProyecto(Integer idProyecto);
}
