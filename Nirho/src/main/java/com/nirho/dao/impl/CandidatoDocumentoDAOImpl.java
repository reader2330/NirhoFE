package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CandidatoDocumentoDAO;
import com.nirho.model.Candidato;
import com.nirho.model.CandidatoDocumento;

@Repository
public class CandidatoDocumentoDAOImpl extends AbstractDAO<CandidatoDocumento, Long> implements CandidatoDocumentoDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CandidatoDocumento> findByCandidato(long idCandidato) {
		String hql = "FROM CandidatoDocumento e WHERE e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCandidato", idCandidato);
		return query.getResultList();
	}
	
}
