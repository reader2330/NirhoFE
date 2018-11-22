package com.nirho.dao;

import java.util.List;

import com.nirho.model.ConsultorProyecto;
import com.nirho.model.ConsultorProyectoPK;

public interface ConsultorProyectoDAO extends BaseDAO<ConsultorProyecto, ConsultorProyectoPK> {
	List<ConsultorProyecto> findByIdUsuario(Integer idUsuario);
}
