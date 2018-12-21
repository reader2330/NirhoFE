package com.nirho.util;

import com.nirho.dto.EmailDatos;

public class EmailUtil {
	public static final String URL_CUESTIONARIO = "http://74.208.252.39/#/encuesta/";
	public static final String URL_CUEST_EVD = "http://74.208.252.39/#/encuesta/EVD/";
	public static final String URL_CUEST_EVA360 = "http://74.208.252.39/#/encuesta/EVA360/";
	public static final String URL_CUEST_APO = "http://74.208.252.39/#/actividades/participante/";
	public static String getAsunto(String nombreProyecto) {
		String asunto = "Cuestionario " + nombreProyecto;
		return asunto;
	}
	public static String getContenido(EmailDatos datos) {
		String contenido = "<p>Estimado(a) " + datos.getNombreParticipante() + "</p>" + 
				"<p>Para ingresar a su cuestionario de participación al proyecto " + datos.getNombreProyecto() +
				", por favor hacer clic en la siguiente liga: " + URL_CUESTIONARIO + datos.getToken() + "</p>" +
				"<p>Saludos</p>";
		return contenido;
	}
	public static String getContenidoEVD(EmailDatos datos) {
		String contenido = "<p>Estimado(a) " + datos.getNombreParticipante() + "</p>" + 
				"<p>Bienvenido a nuestra evaluación de desempeño, para ingresar a su cuestionario de participación al proyecto " + datos.getNombreProyecto() +
				", por favor hacer clic en la siguiente liga: " + URL_CUEST_EVA360 + datos.getToken() + "</p>" +
				"<p>¡Tu participación es muy importante! Por lo que te pedimos te tomes un momento en un " +
				"espacio tranquilo para resolver la siguiente evaluación.</p>" +
				"<p><img src='http://74.208.252.39/logo.bdfd5939a8d871515e82.png'></p>" +
				"<p>Agradecemos tu apoyo</p>";
		return contenido;
	}
	public static String getContenidoEVA360(EmailDatos datos) {
		String contenido = "<p>Estimado(a) " + datos.getNombreParticipante() + "</p>" + 
				"<p>Bienvenido a nuestra evaluación 360˚, para ingresar a su cuestionario de participación al proyecto " + datos.getNombreProyecto() +
				", por favor hacer clic en la siguiente liga: " + URL_CUEST_EVA360 + datos.getToken() + "</p>" +
				"<p>¡Tu participación es muy importante! Por lo que te pedimos te tomes un momento en un " +
				"espacio tranquilo para resolver la siguiente evaluación.</p>" +
				"<p><img src='http://74.208.252.39/logo.bdfd5939a8d871515e82.png'></p>" +
				"<p>Agradecemos tu apoyo</p>";
		return contenido;
	}
	
	public static String getContenidoAPO(EmailDatos datos) {
		String contenido = "<p>Estimado(a) " + datos.getNombreParticipante() + "</p>" + 
				"<p>Para ingresar a su cuestionario de participación al proyecto " + datos.getNombreProyecto() +
				", por favor hacer clic en la siguiente liga: " + URL_CUEST_APO + datos.getToken() + "</p>" +
				"<p>Saludos</p>";
		return contenido;
	}
	
}
