package com.nirho.dto;

import java.io.Serializable;
import java.util.List;

public class ParticipanteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idParticipante;
	private String nombre;	
	private String puesto;
	private Integer idJefeInmediato;
	private List<ParticipanteDTO> subordinados;
	
	public ParticipanteDTO(String nombre, String puesto) {
		this.nombre = nombre;
		this.puesto = puesto;
	}
	
	public ParticipanteDTO(Integer idParticipante, String nombre, String puesto, Integer idJefeInmediato) {
		this.idParticipante = idParticipante;
		this.nombre = nombre;
		this.puesto = puesto;
		this.idJefeInmediato = idJefeInmediato;
	}

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Integer getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(Integer idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}

	public List<ParticipanteDTO> getSubordinados() {
		return subordinados;
	}

	public void setSubordinados(List<ParticipanteDTO> subordinados) {
		this.subordinados = subordinados;
	}

	@Override
	public String toString() {
		return "ParticipanteDTO [idParticipante=" + idParticipante + ", nombre=" + nombre + ", puesto=" + puesto
				+ ", idJefeInmediato=" + idJefeInmediato + ", subordinados=" + subordinados + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idParticipante == null) ? 0 : idParticipante.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParticipanteDTO other = (ParticipanteDTO) obj;
		if (idParticipante == null) {
			if (other.idParticipante != null)
				return false;
		} else if (!idParticipante.equals(other.idParticipante))
			return false;
		return true;
	}
	
	
	
}
