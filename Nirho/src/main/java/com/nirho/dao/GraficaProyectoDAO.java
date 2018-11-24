package com.nirho.dao;

import java.util.List;

import com.nirho.model.GraficaProyecto;
import com.nirho.model.GraficaProyectoPK;

public interface GraficaProyectoDAO extends BaseDAO<GraficaProyecto, GraficaProyectoPK> {
	List<GraficaProyecto> findByIdProyectoIdAreaOrg(Integer idProyecto, String areaOrg);
}
