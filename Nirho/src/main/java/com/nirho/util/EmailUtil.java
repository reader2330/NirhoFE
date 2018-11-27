package com.nirho.util;

import com.nirho.dto.EmailDatos;

public class EmailUtil {
	public static final String URL_CUESTIONARIO = "http://74.208.131.50/#/Nirho/encuesta/";
	public static String getAsunto(String nombreProyecto) {
		String asunto = "Cuestionario " + nombreProyecto;
		return asunto;
	}
	public static String getContenido(EmailDatos datos) {
		String contenido = "<p>Estimado " + datos.getNombreParticipante() + "</p>" + 
				"<p>Para ingresar a su cuestionario de participación al proyecto " + datos.getNombreProyecto() +
				", por favor hacer clic en la siguiente liga: " + URL_CUESTIONARIO + datos.getToken() + "</p>" +
				"<p>Saludos</p>";
		return contenido;
	}
}
