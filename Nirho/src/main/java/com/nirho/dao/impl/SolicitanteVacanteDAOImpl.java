package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.SolicitanteVacanteDAO;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.SolicitanteVacante;

@Repository
public class SolicitanteVacanteDAOImpl extends AbstractDAO<SolicitanteVacante, Long> implements SolicitanteVacanteDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitanteVacante> findByIdConsultor(long idConsultor) {
		String hql = "FROM SolicitanteVacante e WHERE e.idConsultor = :idConsultor";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idConsultor", idConsultor);
		return query.getResultList();
	}
	
}
