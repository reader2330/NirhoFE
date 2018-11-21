package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioEmpresaIRH;

public abstract interface CuestionarioEmpresaIRHDAO extends BaseDAO<CuestionarioEmpresaIRH, Long> {

	public List<CuestionarioEmpresaIRH> findByIdEmpresa(Long idEmpresa);
	
}
