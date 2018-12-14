package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioParticipanteDAO;
import com.nirho.model.CuetionarioParticipante;
import com.nirho.model.CuetionarioParticipantePK;

@Repository
public class CuestionarioParticipanteDAOImpl extends AbstractDAO<CuetionarioParticipante, CuetionarioParticipantePK> implements CuestionarioParticipanteDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<CuetionarioParticipante> findByParticipanteProyecto(Integer idParticipante, Integer idProyecto) {
		String hql = "FROM CuetionarioParticipante cp WHERE cp.cuetionarioParticipantePK.idParticipante = :idParticipante "
													+ "AND cp.cuetionarioParticipantePK.idProyecto = :idProyecto ORDER BY cp.cuetionarioParticipantePK.idPregunta ASC";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idParticipante", idParticipante);
		query.setParameter("idProyecto", idProyecto);
		return query.getResultList();
	}

		
}
