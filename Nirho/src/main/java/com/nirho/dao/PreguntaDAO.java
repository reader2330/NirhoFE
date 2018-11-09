package com.nirho.dao;

import java.util.List;

import com.nirho.model.Pregunta;

public interface PreguntaDAO extends BaseDAO<Pregunta, Integer> {
	List<Pregunta> findByIdTema(Integer idTema);
}
