package com.nirho.dao;

import java.util.List;

import com.nirho.model.Usuario;

public interface UsuarioDAO extends BaseDAO<Usuario, Long> {
	List<Usuario> findByUsername(String username);
	List<Usuario> findByRol(Integer rol);
}
