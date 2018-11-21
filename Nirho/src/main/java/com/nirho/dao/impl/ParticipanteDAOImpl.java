package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ParticipanteDAO;
import com.nirho.model.Participante;
import com.nirho.model.ParticipantePK;

@Repository
public class ParticipanteDAOImpl extends AbstractDAO<Participante, ParticipantePK> implements ParticipanteDAO {
	@Override
	@SuppressWarnings("unchecked")
	public List<Participante> findByIdProyecto(Integer idProyecto) {
		String hql = "FROM Participante p WHERE p.participantePK.idProyecto = :idProyecto";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Participante> findByRfc(String rfc) {
		String hql = "FROM Participante p WHERE p.rfc = :rfc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Participante> findByAreaOrgProyecto(String areaOrg, int idProyecto) {
		String hql = "FROM Participante p WHERE p.participantePK.idProyecto = :idProyecto AND p.areaOrg = :areaOrg";
		Query query = entityManager.createQuery(hql);
		query.setParameter("areaOrg", areaOrg);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}
	
}
