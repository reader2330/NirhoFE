package com.nirho.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.nirho.dao.UsuarioDAO;
import com.nirho.model.Usuario;

@Repository
public class UsuarioDAOImpl extends AbstractDAO<Usuario, Long> implements UsuarioDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findByUsername(String username) {
		String hql = "FROM Usuario a WHERE a.username = :username";
		Query query = entityManager.createQuery(hql);
		query.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Usuario> findByRol(Integer rol) {
		String hql = "FROM Usuario u WHERE u.rol = :rol";
		Query query = entityManager.createQuery(hql);
		query.setParameter("rol", rol);
		return query.getResultList();
	}
	
}
