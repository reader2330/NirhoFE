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
	public List<CuetionarioParticipante> findByIdParticipante(Integer idParticipante) {
		String hql = "FROM CuetionarioParticipante cp WHERE cp.cuetionarioParticipantePK.idParticipante = :idParticipante";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idParticipante", idParticipante);
		return query.getResultList();
	}

		
}
