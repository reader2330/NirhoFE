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
	


}
