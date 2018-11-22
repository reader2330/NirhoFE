package com.nirho.dto;

import java.io.Serializable;

import com.nirho.model.PreguntaTema;
import com.nirho.model.RespuestaPreguntaIRH;

public class PreguntaRespuestaCEmpresaIRHDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4971893682611652467L;
	
	private PreguntaTema pregunta;
	
	private RespuestaPreguntaIRH respuesta;

	/**
	 * @return the pregunta
	 */
	public PreguntaTema getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(PreguntaTema pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the respuesta
	 */
	public RespuestaPreguntaIRH getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(RespuestaPreguntaIRH respuesta) {
		this.respuesta = respuesta;
	}
}
