package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.PreguntaTemaDAO;
import com.nirho.model.PreguntaTema;

@Repository
public class PreguntaTemaDAOImpl extends AbstractDAO<PreguntaTema, Integer> implements PreguntaTemaDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<PreguntaTema> findByIdTema(Integer idTema) {
		String hql = "FROM PreguntaTema p WHERE p.idTema.idTema = :idTema";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idTema", idTema);
		return query.getResultList();
	}
	
}
