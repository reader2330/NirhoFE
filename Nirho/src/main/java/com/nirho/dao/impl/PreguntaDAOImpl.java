package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.PreguntaDAO;
import com.nirho.model.Pregunta;

@Repository
public class PreguntaDAOImpl extends AbstractDAO<Pregunta, Integer> implements PreguntaDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Pregunta> findByIdTema(Integer idTema) {
		String hql = "FROM Pregunta p WHERE p.idTema.idTema = :idTema";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idTema", idTema);
		return query.getResultList();
	}
	
}
