package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class HeadCountAmpliado  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<ParticipanteHCA> lista;
	
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<ParticipanteHCA> getLista() {
		return lista;
	}
	public void setLista(List<ParticipanteHCA> lista) {
		this.lista = lista;
	}
	
	@Override
	public String toString() {
		return "HeadCount [idProyecto=" + idProyecto + ", lista=" + lista + "]";
	}
}
