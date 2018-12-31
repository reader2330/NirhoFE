package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.SolicitanteDAO;
import com.nirho.model.Solicitante;

@Repository
public class SolicitanteDAOImpl extends AbstractDAO<Solicitante, Long> implements SolicitanteDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Solicitante> findByRfc(String rfc) {
		String hql = "FROM Solicitante e WHERE e.rfc = :rfc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return query.getResultList();
	}

}
