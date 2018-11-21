package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.CuestionarioEmpresaIRHDAO;
import com.nirho.model.CuestionarioEmpresaIRH;

@Repository
public class CuestionarioEmpresaIRHDAOImpl extends AbstractDAO<CuestionarioEmpresaIRH, Long> implements CuestionarioEmpresaIRHDAO {
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CuestionarioEmpresaIRH> findByIdEmpresa(Long idEmpresa) {
		String hql = "FROM CuestionarioEmpresaIRH cp WHERE cp.idEmpresa = :idEmpresa";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idEmpresa", idEmpresa);
		return query.getResultList();
	}
	
}
