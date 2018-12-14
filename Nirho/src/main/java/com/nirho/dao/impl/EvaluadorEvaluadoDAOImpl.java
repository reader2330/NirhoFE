package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.EvaluadorEvaluadoDAO;
import com.nirho.model.EvaluadorEvaluado;
import com.nirho.model.EvaluadorEvaluadoPK;

@Repository
public class EvaluadorEvaluadoDAOImpl extends AbstractDAO<EvaluadorEvaluado, EvaluadorEvaluadoPK> implements EvaluadorEvaluadoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<EvaluadorEvaluado> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM EvaluadorEvaluado ev WHERE ev.evaluadorEvaluadoPK.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<EvaluadorEvaluado> findByIdProyectoAndIdEvaluador(Integer idProyecto, Integer idEvaluador) {
		String hql = "FROM EvaluadorEvaluado ev WHERE "
				+ "ev.evaluadorEvaluadoPK.idProyecto = :idProyecto AND ev.evaluadorEvaluadoPK.idEvaluador = :idEvaluador";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		query.setParameter("idEvaluador", idEvaluador);
		return query.getResultList();
	}
	
}
