package com.nirho.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.EntrevistadoDAO;
import com.nirho.model.Entrevistado;

@Repository
public class EntrevistadoDAOImpl extends AbstractDAO<Entrevistado, Integer> implements EntrevistadoDAO {
	
	@Override
	public Entrevistado findByRfcEmpresa(String rfc) throws NoResultException{
		String hql = "select e from Entrevistado e LEFT JOIN e.empresa emp WHERE emp.rfc = :rfc";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rfc", rfc);
		return (Entrevistado) query.getSingleResult();
	}
		
}
