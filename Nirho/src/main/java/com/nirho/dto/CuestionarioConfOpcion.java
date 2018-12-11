package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Opcion;

public class CuestionarioConfOpcion  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<Opcion> lista;
	
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<Opcion> getLista() {
		return lista;
	}
	public void setLista(List<Opcion> lista) {
		this.lista = lista;
	}
	
	@Override
	public String toString() {
		return "CuestionarioConfOpcion [idProyecto=" + idProyecto + ", lista=" + lista + "]";
	}
}
