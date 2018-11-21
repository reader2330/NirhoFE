package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRHTema;

public interface CuestionarioEmpresaIRHTemaService {
	
	public void addCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema e) throws NirhoServiceException;
	public void updateCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema e) throws NirhoServiceException;
	public CuestionarioEmpresaIRHTema getCuestionarioEmpresaIRHTemaById(long id) throws NirhoServiceException;
	public List<CuestionarioEmpresaIRHTema> listCuestionarioEmpresaIRHTema() throws NirhoServiceException;
	public List<CuestionarioEmpresaIRHTema> getListCuestionarioEmpresaIRHTemaByEmpresaId(long id) throws NirhoServiceException;
	public void removeCuestionarioEmpresaIRHTema(CuestionarioEmpresaIRHTema CuestionarioEmpresaIRHTema) throws NirhoServiceException;
	
}
