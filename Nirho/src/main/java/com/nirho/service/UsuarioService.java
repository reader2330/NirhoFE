package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Usuario;

public interface UsuarioService {
	Usuario obtenerUsuario(String username) throws NirhoServiceException;
	List<Usuario> obtenerConsultores() throws NirhoServiceException;
	void guardarAvatar(Usuario usuario) throws NirhoServiceException;
	void guardarUsuario(Usuario usuario) throws NirhoServiceException;
	List<Usuario> getUsuarios() throws NirhoServiceException;
}
