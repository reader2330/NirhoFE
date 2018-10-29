package com.nirho.model.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VW_CUESTIONARIO_RESPUESTAS")
@NamedQueries({
		@NamedQuery(name = "VwCuestionarioRespuestas.findByIdParticipante", 
				query = "SELECT v FROM VwCuestionarioRespuestas v "
				+ " WHERE v.idParticipante =:idParticipante"
				+ " ORDER BY v.idRespuestaPregunta ASC"),
		@NamedQuery(name = "VwCuestionarioRespuestas.findByIdParticipanteAndIdPregunta", 
				query = "SELECT v FROM VwCuestionarioRespuestas v "
				+ " WHERE v.idParticipante =:idParticipante"
				+ " AND v.idPregunta =:idPregunta"
				+ " ORDER BY v.idRespuestaPregunta ASC") })
public class VwCuestionarioRespuestas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_respuesta_pregunta")
	private int idRespuestaPregunta;

	@Column(name = "id_participante")
	private int idParticipante;

	@Column(name = "cumplimiento")
	private String cumplimiento;

	@Column(name = "vigencia")
	private String vigencia;

	@Column(name = "estado")
	private String estado;

	@Column(name = "observaciones")
	private String observaciones;

	@Column(name = "inversion_aproximada")
	private String inversionAproximada;
	
	@Column(name = "id_pregunta")
	private int idPregunta;

	@Column(name = "enunciado")
	private String enunciado;

	@Column(name = "tipo")
	private int tipo;

	@Column(name = "de_plantilla")
	private int dePlantilla;

	@Column(name = "documento_referencia")
	private String documentoReferencia;

	public int getIdRespuestaPregunta() {
		return idRespuestaPregunta;
	}

	public void setIdRespuestaPregunta(int idRespuestaPregunta) {
		this.idRespuestaPregunta = idRespuestaPregunta;
	}

	public int getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(int idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getCumplimiento() {
		return cumplimiento;
	}

	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getInversionAproximada() {
		return inversionAproximada;
	}

	public void setInversionAproximada(String inversionAproximada) {
		this.inversionAproximada = inversionAproximada;
	}

	public int getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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

	public String getDocumentoReferencia() {
		return documentoReferencia;
	}

	public void setDocumentoReferencia(String documentoReferencia) {
		this.documentoReferencia = documentoReferencia;
	}

	@Override
	public String toString() {
		return "VwCuestionarioRespuestas [idRespuestaPregunta=" + idRespuestaPregunta + ", idParticipante="
				+ idParticipante + ", cumplimiento=" + cumplimiento + ", vigencia=" + vigencia + ", estado=" + estado
				+ ", observaciones=" + observaciones + ", inversionAproximada=" + inversionAproximada + ", idPregunta="
				+ idPregunta + ", enunciado=" + enunciado + ", tipo=" + tipo + ", dePlantilla=" + dePlantilla
				+ ", documentoReferencia=" + documentoReferencia + "]";
	}

	
}
