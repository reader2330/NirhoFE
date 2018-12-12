package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioOpcion;
import com.nirho.model.CuestionarioOpcionPK;

public interface CuestionarioOpcionDAO extends BaseDAO<CuestionarioOpcion, CuestionarioOpcionPK> {
	List<CuestionarioOpcion> findByIdProyecto(Integer idProyecto);
	List<CuestionarioOpcion> findByIdProyectoAndIdTema(Integer idProyecto, Integer idTema);
}
