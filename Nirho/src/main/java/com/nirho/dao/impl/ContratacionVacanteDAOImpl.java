package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ContratacionVacanteDAO;
import com.nirho.model.ContratacionVacante;
import com.nirho.model.EntrevistaVacante;

@Repository
public class ContratacionVacanteDAOImpl extends AbstractDAO<ContratacionVacante, Long> implements ContratacionVacanteDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContratacionVacante> findByIdCandidato(long idCandidato) {
		String hql = "FROM ContratacionVacante e WHERE e.idCandidato = :idCandidato";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCandidato", idCandidato);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ContratacionVacante> findByIdVacante(long idVacante) {
		String hql = "FROM ContratacionVacante e WHERE e.idVacante = :idVacante";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idVacante", idVacante);
		return query.getResultList();
	}
	
}
