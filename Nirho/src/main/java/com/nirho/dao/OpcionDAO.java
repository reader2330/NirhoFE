package com.nirho.dao;

import java.util.List;

import com.nirho.model.Opcion;

public interface OpcionDAO extends BaseDAO<Opcion, Integer> {
	List<Opcion> findByIdTema(Integer idTema);
}
