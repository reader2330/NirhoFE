package com.nirho.dao;

import java.util.List;

import com.nirho.model.PreguntaTema;

public interface PreguntaTemaDAO extends BaseDAO<PreguntaTema, Integer> {
	List<PreguntaTema> findByIdTema(Integer idTema);
}
