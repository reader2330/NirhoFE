package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.UsuarioDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Usuario;
import com.nirho.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	public final static Logger logger = Logger.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioDAO dao;
	
	@Override
	public Usuario obtenerUsuario(String username) throws NirhoServiceException {
		Usuario usuario = null;
		try {
			usuario = dao.findByUsername(username).get(0);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al obtener el usuarioe [" + username+ "] en la base de datos");
		}
		return usuario;
	}
	
	@Override
	public List<Usuario> obtenerConsultores() throws NirhoServiceException {
		List<Usuario> consultores = new ArrayList<>();
		try {
			consultores = dao.findByRol(new Integer("3"));
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return consultores;
	}

	@Override
	public void guardarAvatar(Usuario usuario) throws NirhoServiceException {
		try {
			dao.update(usuario);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardarUsuario(Usuario usuario) throws NirhoServiceException {
		try {
			List<Usuario> l = dao.findByUsername(usuario.getUsername());
			if(l == null || l.isEmpty()) {
				dao.save(usuario);
			} else {
				dao.update(usuario);
			}
		}catch (Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	@Override
	public List<Usuario> getUsuarios() throws NirhoServiceException{
		List<Usuario> lista = null;
		lista = dao.findAll();
		return lista;
	}
}
