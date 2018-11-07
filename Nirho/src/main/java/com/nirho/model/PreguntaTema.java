package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pregunta_tema")
public class PreguntaTema implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id_pregunta")
	private Integer idPregunta;
	@Basic(optional = false)
	@Column(name = "enunciado")
	private String enunciado;
	@Column(name = "documento_referencia")
	private String documentoReferencia;
	@Basic(optional = false)
	@Column(name = "tipo")
	private int tipo;
	@Basic(optional = false)
	@Column(name = "de_plantilla")
	private int dePlantilla;
	@ManyToOne
	@JoinColumn(name = "id_tema", referencedColumnName = "id_tema")
	private TemaCuestionario idTema;
	
	public PreguntaTema() {
	}

	public PreguntaTema(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public PreguntaTema(Integer idPregunta, String enunciado, int tipo,
			int dePlantilla) {
		this.idPregunta = idPregunta;
		this.enunciado = enunciado;
		this.tipo = tipo;
		this.dePlantilla = dePlantilla;
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getDocumentoReferencia() {
		return documentoReferencia;
	}

	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getDePlantilla() {
		return dePlantilla;
	}

	public void setDePlantilla(int dePlantilla) {
		this.dePlantilla = dePlantilla;
	}
	
	public TemaCuestionario getIdTema() {
		return idTema;
	}

	public void setIdTema(TemaCuestionario idTema) {
		this.idTema = idTema;
	}

	public String toString(){
		return "PreguntaTema [idPregunta=" + idPregunta + ", enunciado=" + enunciado + ", tipo=" + tipo + ", dePlantilla=" + dePlantilla + ", idTema=" + idTema + "]";
	}
}
