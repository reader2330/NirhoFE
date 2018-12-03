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
@Table(name = "pregunta_tema_historico")
public class PreguntaTemaHistorico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388169816620391442L;

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
	private TemaCuestionario tema;

	@ManyToOne
	@JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable =false , updatable = false)
	private PreguntaTema preguntaTema;

	/**
	 * @return the idPregunta
	 */
	public Integer getIdPregunta() {
		return idPregunta;
	}

	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	/**
	 * @return the enunciado
	 */
	public String getEnunciado() {
		return enunciado;
	}

	/**
	 * @param enunciado the enunciado to set
	 */
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	/**
	 * @return the documentoReferencia
	 */
	public String getDocumentoReferencia() {
		return documentoReferencia;
	}

	/**
	 * @param documentoReferencia the documentoReferencia to set
	 */
	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the dePlantilla
	 */
	public int getDePlantilla() {
		return dePlantilla;
	}

	/**
	 * @param dePlantilla the dePlantilla to set
	 */
	public void setDePlantilla(int dePlantilla) {
		this.dePlantilla = dePlantilla;
	}

	/**
	 * @return the tema
	 */
	public TemaCuestionario getTema() {
		return tema;
	}

	/**
	 * @param tema the tema to set
	 */
	public void setTema(TemaCuestionario tema) {
		this.tema = tema;
	}

	/**
	 * @return the preguntaTema
	 */
	public PreguntaTema getPreguntaTema() {
		return preguntaTema;
	}

	/**
	 * @param preguntaTema the preguntaTema to set
	 */
	public void setPreguntaTema(PreguntaTema preguntaTema) {
		this.preguntaTema = preguntaTema;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PreguntaTemaHistorico [idPregunta=" + idPregunta
				+ ", enunciado=" + enunciado + ", documentoReferencia="
				+ documentoReferencia + ", tipo=" + tipo + ", dePlantilla="
				+ dePlantilla + ", tema=" + tema + ", preguntaTema="
				+ preguntaTema + "]";
	}
}
