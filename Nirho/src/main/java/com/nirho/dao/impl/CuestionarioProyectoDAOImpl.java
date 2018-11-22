package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioProyectoDAO;
import com.nirho.model.CuestionarioProyecto;
import com.nirho.model.CuestionarioProyectoPK;

@Repository
public class CuestionarioProyectoDAOImpl extends AbstractDAO<CuestionarioProyecto, CuestionarioProyectoPK> implements CuestionarioProyectoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioProyecto> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM CuestionarioProyecto cp WHERE cp.cuestionarioProyectoPK.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
}
