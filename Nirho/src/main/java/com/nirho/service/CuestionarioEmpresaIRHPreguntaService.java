package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRHPregunta;

public interface CuestionarioEmpresaIRHPreguntaService {
	
	public void addCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta e) throws NirhoServiceException;
	public void updateCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta e) throws NirhoServiceException;
	public CuestionarioEmpresaIRHPregunta getCuestionarioEmpresaIRHPreguntaById(long id) throws NirhoServiceException;
	public List<CuestionarioEmpresaIRHPregunta> listCuestionarioEmpresaIRHPregunta() throws NirhoServiceException;
	public void removeCuestionarioEmpresaIRHPregunta(CuestionarioEmpresaIRHPregunta cuestionarioEmpresaIRHPregunta) throws NirhoServiceException;
	
}
