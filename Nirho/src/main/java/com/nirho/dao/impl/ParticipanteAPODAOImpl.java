package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ParticipanteAPODAO;
import com.nirho.model.ParticipanteAPO;

@Repository
public class ParticipanteAPODAOImpl extends AbstractDAO<ParticipanteAPO, Integer> implements ParticipanteAPODAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ParticipanteAPO> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM ParticipanteAPO p WHERE p.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	 
}
