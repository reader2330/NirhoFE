package com.nirho.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.RoleModuloSubModuloAPODAO;
import com.nirho.model.RoleModuloSubModuloAPO;
import com.nirho.service.RoleModuloSubModuloAPOService;

@Repository
public class RoleModuloSubModuloAPODAOImpl extends AbstractDAO<RoleModuloSubModuloAPO, Integer> implements RoleModuloSubModuloAPODAO {
	
	public List<RoleModuloSubModuloAPO> obtenerSubModulos(int rol){
		String hql = "FROM RoleModuloSubModuloAPO u WHERE u.idRole = :rol";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rol", rol);
		return query.getResultList();
	}
	
}
