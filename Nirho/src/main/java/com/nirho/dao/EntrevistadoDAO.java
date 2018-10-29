package com.nirho.dao;

import com.nirho.model.Entrevistado;

public interface EntrevistadoDAO extends BaseDAO<Entrevistado, Integer> {
	public Entrevistado findByRfcEmpresa(String rfc); 
}
