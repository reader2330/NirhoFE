package com.nirho.dao;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;

public interface EmpresaDAO extends BaseDAO<Empresa, Long> {
	List<Empresa> findByRfc(String rfc); 
	void eliminarEmpresa(Empresa empresa) throws NirhoServiceException;
	List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoServiceException;
}
