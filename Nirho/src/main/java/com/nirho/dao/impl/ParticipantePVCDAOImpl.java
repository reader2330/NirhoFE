package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ParticipantePVCDAO;
import com.nirho.model.ParticipantePVC;

@Repository
public class ParticipantePVCDAOImpl extends AbstractDAO<ParticipantePVC, Integer> implements ParticipantePVCDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ParticipantePVC> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM ParticipantePVC p WHERE p.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	 
}
