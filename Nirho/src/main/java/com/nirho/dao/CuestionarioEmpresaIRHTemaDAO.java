package com.nirho.dao;

import java.util.List;

import com.nirho.model.CuestionarioEmpresaIRHTema;

public abstract interface CuestionarioEmpresaIRHTemaDAO extends BaseDAO<CuestionarioEmpresaIRHTema, Long> {

	public List<CuestionarioEmpresaIRHTema> findByIdEmpresa(Long idEmpresa);
	
}
