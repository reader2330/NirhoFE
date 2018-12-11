package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

import com.nirho.model.Tema;

public class CuestionarioConfEVD  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<Tema> lista;
	
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<Tema> getLista() {
		return lista;
	}
	public void setLista(List<Tema> lista) {
		this.lista = lista;
	}
	
	@Override
	public String toString() {
		return "CuestionarioConfTema [idProyecto=" + idProyecto + ", lista=" + lista + "]";
	}
}
