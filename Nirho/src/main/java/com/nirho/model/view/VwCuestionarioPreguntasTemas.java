package com.nirho.model.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VW_CUESTIONARIO_PREGUNTAS_TEMAS")
@NamedQueries({
		@NamedQuery(name = "VwCuestionarioPreguntasTemas.findByIdTema", 
				query = "SELECT v FROM VwCuestionarioPreguntasTemas v "
				+ " WHERE v.idTema =:idTema"
				+ " ORDER BY v.idPregunta ASC") })
public class VwCuestionarioPreguntasTemas implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pregunta")
	private int idPregunta;
	
	@Column(name = "id_tema")
	private int idTema;

	@Column(name = "nombre_tema")
	private String nombreTema;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "enunciado")
	private String enunciado;

	@Column(name = "tipo")
	private int tipo;

	@Column(name = "de_plantilla")
	private int dePlantilla;

	@Column(name = "documento_referencia")
	private String documentoReferencia;
	
	@Transient
	private VwCuestionarioRespuestas respuesta;

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getNombreTema() {
		return nombreTema;
	}

	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public VwCuestionarioRespuestas getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(VwCuestionarioRespuestas respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "VwCuestionarioPreguntasTemas [idPregunta=" + idPregunta + ", idTema=" + idTema + ", nombreTema="
				+ nombreTema + ", descripcion=" + descripcion + ", enunciado=" + enunciado + ", tipo=" + tipo
				+ ", dePlantilla=" + dePlantilla + ", documentoReferencia=" + documentoReferencia + ", respuesta="
				+ respuesta + "]";
	}

}
