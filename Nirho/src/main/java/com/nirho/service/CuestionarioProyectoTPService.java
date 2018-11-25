package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.CuestionarioProyectoTP;

public interface CuestionarioProyectoTPService {
	
	public void addCuestionarioProyectoTP(CuestionarioProyectoTP e) throws NirhoServiceException;
	public void updateCuestionarioProyectoTP(CuestionarioProyectoTP e) throws NirhoServiceException;
	public CuestionarioProyectoTP getCuestionarioProyectoTPById(long id) throws NirhoServiceException;
	public List<CuestionarioProyectoTP> listCuestionarioProyectoTP() throws NirhoServiceException;
	public List<CuestionarioProyectoTP> getListCuestionarioProyectoTPByProyectoId(long id) throws NirhoServiceException;
	public void removeCuestionarioProyectoTP(CuestionarioProyectoTP CuestionarioProyectoTP) throws NirhoServiceException;
	
}
