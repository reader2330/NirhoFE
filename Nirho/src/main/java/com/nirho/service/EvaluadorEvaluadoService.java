package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.EvaluadorEvaluado;

public interface EvaluadorEvaluadoService {
	void guardar(EvaluadorEvaluado evaluador) throws NirhoServiceException;
	List<EvaluadorEvaluado> obtenerEvaluados(Integer idProyecto) throws NirhoServiceException;
}
