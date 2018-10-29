package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ParticipanteDAO;
import com.nirho.model.Participante;

@Repository
public class ParticipanteDAOImpl extends AbstractDAO<Participante, Integer> implements ParticipanteDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Participante> findByIdEmpresa(Long idEmpresa) {
		String hql = "FROM Participante p WHERE p.idEmpresa.id = :idEmpresa";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
}
