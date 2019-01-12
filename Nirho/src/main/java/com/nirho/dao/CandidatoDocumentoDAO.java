package com.nirho.dao;

import java.util.List;

import com.nirho.model.CandidatoDocumento;

public interface CandidatoDocumentoDAO extends BaseDAO<CandidatoDocumento, Long> {

	public List<CandidatoDocumento> findByCandidato(long idCandidato);
	
}
