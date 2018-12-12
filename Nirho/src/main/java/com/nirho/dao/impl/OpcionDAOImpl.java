package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.OpcionDAO;
import com.nirho.model.Opcion;

@Repository
public class OpcionDAOImpl extends AbstractDAO<Opcion, Integer> implements OpcionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Opcion> findByIdTema(Integer idTema) {
		String hql = "FROM Opcion op WHERE op.idTema.idTema = :idTema AND op.idOpcion < "+idTema+"006";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idTema", idTema);
		return query.getResultList();
	}
	
}
