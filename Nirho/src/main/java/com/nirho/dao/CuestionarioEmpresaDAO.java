package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioEmpresa;
import com.nirho.model.view.VwCuestionarioPreguntasTemas;
import com.nirho.model.view.VwCuestionarioRespuestas;
import com.nirho.model.view.VwTemaCuestionario;

public abstract interface CuestionarioEmpresaDAO extends BaseDAO<CuestionarioEmpresa, Integer> {
	
	public abstract List<CuestionarioEmpresa> consultarPorRFC(String paramString);

	public abstract CuestionarioEmpresa consultarPreguntasTemaEmpresa(Integer paramInteger);

	List<VwCuestionarioPreguntasTemas> consultarPreguntasTemaCuestionario(int idTema);

	List<VwCuestionarioRespuestas> consultarRespuestaCuestionario(int idParticipante);

	VwCuestionarioRespuestas consultarRespuestaCuestionario(int idParticipante, int idPregunta);
	
	CuestionarioEmpresa consultarCuestionarioEmpresa(int idTema);

	List<VwTemaCuestionario> consultarTemasCuestionario();

}
