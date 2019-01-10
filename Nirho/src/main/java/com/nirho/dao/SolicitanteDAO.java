package com.nirho.dao;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.Solicitante;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;

public interface SolicitanteDAO extends BaseDAO<Solicitante, Long> {
	List<Solicitante> findByRfc(String rfc); 
	List<Solicitante> findByUsername(String username);
}
