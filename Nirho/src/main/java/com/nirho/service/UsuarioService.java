package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Usuario;

public interface UsuarioService {
	List<Usuario> obtenerConsultores() throws NirhoServiceException;
}
