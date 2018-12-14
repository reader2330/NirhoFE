package com.nirho.dao;

import java.util.List;

import com.nirho.model.EvaluadorEvaluado;
import com.nirho.model.EvaluadorEvaluadoPK;

public interface EvaluadorEvaluadoDAO extends BaseDAO<EvaluadorEvaluado, EvaluadorEvaluadoPK> {
	List<EvaluadorEvaluado> findByIdProyecto(Integer idProyecto);
	List<EvaluadorEvaluado> findByIdProyectoAndIdEvaluador(Integer idProyecto, Integer idEvaluador);
}
