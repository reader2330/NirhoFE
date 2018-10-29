package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dao.UsuarioDAO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ClbSubmodulo;
import com.nirho.model.Usuario;
import com.nirho.service.RolClbService;
import com.nirho.service.UsuarioService;
import com.nirho.util.SessionConstants;
import com.nirho.util.SessionUtil;

@RestController
@RequestMapping( value = "/usuario" )
public class UsuarioController {
	public final static Logger logger = Logger.getLogger(UsuarioController.class);
	@Autowired
	UsuarioDAO dao;
	@Autowired
	RolClbService rolService;
	@Autowired
	UsuarioService usuarioService;
		
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public void login(@RequestBody Usuario usuario, HttpServletRequest request) throws NirhoControllerException {
		try {
			List<Usuario> usuarioList = dao.findByUsername(usuario.getUsername());
			if(usuarioList != null && !usuarioList.isEmpty()) {
				logger.info("usuario session [" + usuarioList.get(0) +"]");
				HttpSession httpSession = request.getSession(true);
				httpSession.setAttribute(SessionConstants.USUARIO_ATTRIBUTE, usuarioList.get(0));
			} else {
				throw new NirhoControllerException("Usuario no registrado");
			}
		} catch (NirhoControllerException e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException(e.getMessage());
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() +"]");
			throw new NirhoControllerException("Problemas al conectar con la BD");
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request) {
		logger.info("******************** cerrando session ******************");
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
	}
	
	@RequestMapping(value = "/submodulosClb", method = RequestMethod.GET)
	public List<ClbSubmodulo> submodulosClb(HttpServletRequest request) {
		List<ClbSubmodulo> submodulos = null;
		if (request.getSession(false) != null) {
			Usuario usuario = SessionUtil.getUsuarioSession(request);
			try {
				submodulos = rolService.obtenerSubModulos(usuario.getRol());
			} catch (NirhoServiceException e) {
				logger.info("Exception [" + e.getMessage() +"]");
			}
		}
		return submodulos;
	}
	
	@RequestMapping(value = "/consultores", method = RequestMethod.GET)
	public List<Usuario> consultores() {
		List<Usuario> consultores = new ArrayList<>();
		try {
			consultores = usuarioService.obtenerConsultores();
		} catch (NirhoServiceException e) {
			logger.info("Exception [" + e.getMessage() +"]");
		}
		return consultores;
	}

	@RequestMapping(value = "/getUsuarioLogueado", method = RequestMethod.GET)
	@ResponseBody
	public Usuario getUsuarioLogueado(HttpServletRequest request){
		Usuario usuarioEnSesion = SessionUtil.getUsuarioSession(request);
		return usuarioEnSesion;
	}
}
