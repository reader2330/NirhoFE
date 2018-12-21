package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.nirho.dao.ProyectoPVCAreaDAO;
import com.nirho.model.ProyectoPVCArea;

public class ProyectoPVCAreaDAOImpl extends AbstractDAO<ProyectoPVCArea, Integer> implements ProyectoPVCAreaDAO {
	 
	@Override
	@SuppressWarnings("unchecked")
	public List<ProyectoPVCArea> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM ProyectoPVCArea p WHERE p.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
}
