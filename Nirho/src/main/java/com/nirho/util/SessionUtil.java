package com.nirho.util;

import javax.servlet.http.HttpServletRequest;

import com.nirho.model.Usuario;

public class SessionUtil {
	public static Usuario getUsuarioSession(HttpServletRequest request) {
		Usuario usuario = null;
		if (request.getSession(false) != null) {
			usuario = (Usuario) request.getSession(false).getAttribute(SessionConstants.USUARIO_ATTRIBUTE);
		}
		return usuario;
	}
}
