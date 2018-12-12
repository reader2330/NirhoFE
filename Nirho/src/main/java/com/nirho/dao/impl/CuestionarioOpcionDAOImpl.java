package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioOpcionDAO;
import com.nirho.model.CuestionarioOpcion;
import com.nirho.model.CuestionarioOpcionPK;

@Repository
public class CuestionarioOpcionDAOImpl extends AbstractDAO<CuestionarioOpcion, CuestionarioOpcionPK> implements CuestionarioOpcionDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioOpcion> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM CuestionarioOpcion co WHERE co.cuestionarioOpcionPK.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioOpcion> findByIdProyectoAndIdTema(Integer idProyecto, Integer idTema) {
		String hql = "FROM CuestionarioOpcion co "
				+ "WHERE co.cuestionarioOpcionPK.idProyecto = :idProyecto AND co.cuestionarioOpcionPK.idTema = :idTema";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		query.setParameter("idTema", idTema);
		return query.getResultList();
	}
	
}
