package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CandidatoDAO;
import com.nirho.model.Candidato;
import com.nirho.model.Solicitante;

@Repository
public class CandidatoDAOImpl extends AbstractDAO<Candidato, Long> implements CandidatoDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidato> findByUsername(String username) {
		String hql = "FROM Candidato e WHERE e.username = :username";
		Query query = entityManager.createQuery(hql);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidato> findByRfc(String rfc) {
		String hql = "FROM Candidato e WHERE e.rfc = :rfc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Candidato> findByVacante(long idVacante) {
		String hql = "FROM Candidato e WHERE e.idVacante = :idVacante";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idVacante", idVacante);
		return query.getResultList();
	}
	
}
