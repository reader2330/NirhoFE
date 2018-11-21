package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioEmpresaIRHTemaDAO;
import com.nirho.model.CuestionarioEmpresaIRHTema;

@Repository
public class CuestionarioEmpresaIRHTemaDAOImpl extends AbstractDAO<CuestionarioEmpresaIRHTema, Long> implements CuestionarioEmpresaIRHTemaDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioEmpresaIRHTema> findByIdEmpresa(Long idEmpresa) {
		String hql = "FROM CuestionarioEmpresaIRHTema cp WHERE cp.idEmpresa = :idEmpresa";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
}
