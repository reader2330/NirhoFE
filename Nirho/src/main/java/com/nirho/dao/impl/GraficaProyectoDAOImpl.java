package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.GraficaProyectoDAO;
import com.nirho.model.GraficaProyecto;
import com.nirho.model.GraficaProyectoPK;

@Repository
public class GraficaProyectoDAOImpl extends AbstractDAO<GraficaProyecto, GraficaProyectoPK>
		implements GraficaProyectoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<GraficaProyecto> findByIdProyectoIdAreaOrg(Integer idProyecto, String areaOrg) {
		String hql = "FROM GraficaProyecto gp WHERE gp.gaficaProyectoPK.idProyecto = :idProyecto "
				+ "AND gp.gaficaProyectoPK.areaOrg = :areaOrg";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		query.setParameter("areaOrg", areaOrg);
		return query.getResultList();
	}

}
