package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ConsultorProyectoDAO;
import com.nirho.model.ConsultorProyecto;
import com.nirho.model.ConsultorProyectoPK;

@Repository
public class ConsultorProyectoDAOImpl extends AbstractDAO<ConsultorProyecto, ConsultorProyectoPK> implements ConsultorProyectoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<ConsultorProyecto> findByIdUsuario(Integer idUsuario) {
		String hql = "FROM ConsultorProyecto cp WHERE cp.consultorProyectoPK.idUsuario = :idUsuario";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}
	
}
