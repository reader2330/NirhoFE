package com.nirho.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("1")
@Table(name = "respuesta_pregunta_irh")
public class RespuestaPreguntaIRH extends RespuestaPregunta {
	
	private static final long serialVersionUID = 996624305593720227L;
	
	@Column(name = "cumplimiento")
	private String cumplimiento;
	
	@Column(name = "vigencia")
	private String vigencia;
	
	@Column(name = "estado")
	private String estado;	
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "inversion_aproximada")
	private double inversionAproximada;
	
	@Transient
	private int idTema;
	
	@Transient
	private boolean finalizado;

	/**
	 * Constructor vacio
	 */
	public RespuestaPreguntaIRH() {
		super();
	}

	/**
	 * @return the cumplimiento
	 */
	public String getCumplimiento() {
		return cumplimiento;
	}

	/**
	 * @param cumplimiento the cumplimiento to set
	 */
	public void setCumplimiento(String cumplimiento) {
		this.cumplimiento = cumplimiento;
	}

	/**
	 * @return the vigencia
	 */
	public String getVigencia() {
		return vigencia;
	}

	/**
	 * @param vigencia the vigencia to set
	 */
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the inversionAproximada
	 */
	public double getInversionAproximada() {
		return inversionAproximada;
	}

	/**
	 * @param inversionAproximada the inversionAproximada to set
	 */
	public void setInversionAproximada(double inversionAproximada) {
		this.inversionAproximada = inversionAproximada;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RespuestaPreguntaIRH [getCumplimiento()=" + getCumplimiento()
				+ ", getVigencia()=" + getVigencia() + ", getEstado()="
				+ getEstado() + ", getObservaciones()=" + getObservaciones()
				+ ", getInversionAproximada()=" + getInversionAproximada()
				+ ", getIdRespuestaPregunta()=" + getIdRespuestaPregunta()
				+ ", getIdParticipante()=" + getIdParticipante()
				+ ", getIdPregunta()=" + getIdPregunta()
				+ ", getRespuestaNumero()=" + getRespuestaNumero()
				+ ", getRespuestaTexto()=" + getRespuestaTexto()
				+ ", getEstaContestada()=" + getEstaContestada()
				+ ", getIdCuestionarioEmpresa()=" + getIdCuestionarioEmpresa()
				+ ", getCuestionarioEmpresa()=" + getCuestionarioEmpresa()
				+ "]";
	}
}
