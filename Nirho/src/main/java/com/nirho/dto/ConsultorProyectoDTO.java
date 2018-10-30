package com.nirho.dto;

import java.io.Serializable;

public class ConsultorProyectoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String idUsuario;
	private String idProyecto;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	@Override
	public String toString() {
		return "ConsultorProyectoDTO [idUsuario=" + idUsuario + ", idProyecto=" + idProyecto + "]";
	}
}
