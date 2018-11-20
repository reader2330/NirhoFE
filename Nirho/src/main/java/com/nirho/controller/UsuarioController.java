package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ClbSubmodulo;
import com.nirho.model.Usuario;
import com.nirho.security.TokenHelper;
import com.nirho.service.RolClbService;
import com.nirho.service.UsuarioService;
import com.nirho.util.SessionUtil;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/usuario" )
public class UsuarioController {
	public final static Logger logger = Logger.getLogger(UsuarioController.class);
	
	@Autowired
	RolClbService rolService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
    TokenHelper tokenHelper;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public String login(@RequestBody Usuario usuario, HttpServletRequest request) throws NirhoControllerException {
		try {
			Usuario usuarioSession = usuarioService.obtenerUsuario(usuario.getUsername());
			if(!SessionUtil.getEncryptMD5(usuario.getPassword().trim()).equals(usuarioSession.getPassword())) {
				throw new NirhoControllerException("Password incorrecto");
			}
			logger.info("usuario session [" + usuarioSession +"]");
			String jwt = tokenHelper.generateToken( usuarioSession.getUsername(), usuarioSession.getRol() );
	        return "{\"token\": \"" + jwt + "\"}";
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
	public ResponseEntity<?> submodulosClb(HttpServletRequest request) {
        if(request.getAttribute("username") != null) {
        	List<ClbSubmodulo> submodulos = null;
			try {
				submodulos = rolService.obtenerSubModulos((int)request.getAttribute("role"));
			} catch (NirhoServiceException e) {
				logger.info("Exception [" + e.getMessage() +"]");
			}
    		return new ResponseEntity<>(submodulos, HttpStatus.OK);	
        } else {
        	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); 
        }
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
	public ResponseEntity<?> getUsuarioLogueado(HttpServletRequest request) throws NirhoControllerException{
		if(request.getAttribute("username") != null) {
			try {
				Usuario usuarioEnSesion = usuarioService.obtenerUsuario((String)request.getAttribute("username"));
    			return new ResponseEntity<>(usuarioEnSesion, HttpStatus.OK);	
			} catch (NirhoServiceException e) {
				logger.info("Exception [" + e.getMessage() +"]");
				throw new NirhoControllerException(e.getMessage());
			}
        } else {
        	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); 
        }
	}
	
	@RequestMapping(value = "/usuarioEnSesion", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getUsuario(HttpServletRequest request) throws NirhoControllerException {
		if(request.getAttribute("username") != null) {
			try {
				Usuario usuarioEnSesion = usuarioService.obtenerUsuario((String)request.getAttribute("username"));
				logger.info("Usuario en Session ["+usuarioEnSesion+"]");
				return new ResponseEntity<>(usuarioEnSesion, HttpStatus.OK);	
			} catch (NirhoServiceException e) {
				logger.info("Exception [" + e.getMessage() +"]");
				throw new NirhoControllerException(e.getMessage());
			}
        } else {
        	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); 
        }
	}
	
	@RequestMapping(value = "/guardarAvatar", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> guardarAvatar(HttpServletRequest request, @RequestParam(name="ruta") String ruta) throws NirhoControllerException {
		if(request.getAttribute("username") != null) {
			try {
				Usuario usuario = usuarioService.obtenerUsuario((String)request.getAttribute("username"));
				usuario.setAvatar(ruta);
				usuarioService.guardarAvatar(usuario);
				return new ResponseEntity<>("", HttpStatus.OK);
			} catch (NirhoServiceException e) {
				logger.info("Exception [" + e.getMessage() +"]");
				throw new NirhoControllerException("Problemas en la BD al guardar el avatar");
			}
        } else {
        	return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED); 
        }
	}
}
