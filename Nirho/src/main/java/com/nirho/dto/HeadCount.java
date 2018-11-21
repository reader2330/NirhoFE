package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class HeadCount  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idProyecto;
	private List<ParticipanteHC> lista;
	
	public Integer getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List<ParticipanteHC> getLista() {
		return lista;
	}
	public void setLista(List<ParticipanteHC> lista) {
		this.lista = lista;
	}
	
	@Override
	public String toString() {
		return "HeadCount [idProyecto=" + idProyecto + ", lista=" + lista + "]";
	}
}
