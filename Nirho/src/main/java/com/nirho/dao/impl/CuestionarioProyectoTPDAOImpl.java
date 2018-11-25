package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioProyectoTPDAO;
import com.nirho.model.CuestionarioProyectoTP;

@Repository
public class CuestionarioProyectoTPDAOImpl extends AbstractDAO<CuestionarioProyectoTP, Long> implements CuestionarioProyectoTPDAO {
	 
	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioProyectoTP> findByIdProyecto(Long idProyecto) {
		String hql = "FROM CuestionarioProyectoTP cp WHERE cp.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
}
