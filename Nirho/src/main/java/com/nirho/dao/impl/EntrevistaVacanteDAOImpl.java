package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.EntrevistaVacanteDAO;
import com.nirho.model.EntrevistaVacante;
import com.nirho.model.Solicitante;

@Repository
public class EntrevistaVacanteDAOImpl extends AbstractDAO<EntrevistaVacante, Long> implements EntrevistaVacanteDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntrevistaVacante> findByIdCandidato(long idCandidato) {
		String hql = "FROM EntrevistaVacante e WHERE e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCandidato", idCandidato);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntrevistaVacante> findByIdConsultor(long idConsultor) {
		String hql = "FROM EntrevistaVacante e WHERE e.idConsultor = :idConsultor";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idConsultor", idConsultor);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntrevistaVacante> findByIdSolicitante(long idSolicitante) {
		String hql = "FROM EntrevistaVacante e WHERE e.idSolicitante = :idSolicitante";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idSolicitante", idSolicitante);
		return query.getResultList();
	}
	
}
