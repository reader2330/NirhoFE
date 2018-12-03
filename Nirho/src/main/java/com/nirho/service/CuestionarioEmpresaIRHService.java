package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioEmpresaIRH;

public interface CuestionarioEmpresaIRHService {
	
	public void addCuestionarioEmpresaIRH(CuestionarioEmpresaIRH e) throws NirhoServiceException;
	public void updateCuestionarioEmpresaIRH(CuestionarioEmpresaIRH e) throws NirhoServiceException;
	public CuestionarioEmpresaIRH getCuestionarioEmpresaIRHById(long id) throws NirhoServiceException;
	public List<CuestionarioEmpresaIRH> listCuestionarioEmpresaIRH() throws NirhoServiceException;
	public List<CuestionarioEmpresaIRH> getListCuestionarioEmpresaIRHByEmpresaId(long id) throws NirhoServiceException;
	public void removeCuestionarioEmpresaIRH(CuestionarioEmpresaIRH CuestionarioEmpresaIRH) throws NirhoServiceException;
	
}
