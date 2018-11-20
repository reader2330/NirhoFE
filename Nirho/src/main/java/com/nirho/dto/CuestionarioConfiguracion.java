package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Pregunta;

public class CuestionarioConfiguracion  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<Pregunta> lista;
	
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<Pregunta> getLista() {
		return lista;
	}
	public void setLista(List<Pregunta> lista) {
		this.lista = lista;
	}
	
	@Override
	public String toString() {
		return "CuestionarioConfiguracion [idProyecto=" + idProyecto + ", lista=" + lista + "]";
	}
}
