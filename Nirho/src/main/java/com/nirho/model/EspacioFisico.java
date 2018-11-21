package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author AAG
 */
@Entity
@Table(name = "espacio_fisico")
public class EspacioFisico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2351147498649212310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "instalaciones_adecuadas", nullable = false)
	private boolean instalacionesAdecuadas;
	
	@Column(name = "luz_suficiente_adecuada", nullable = false)
	private boolean luzAdecuada;
	
	@Column(name = "temperatura_adecuada", nullable = false)
	private boolean temperaturaAdecuada;
	
	@Column(name = "posiciones_trabajo_comodas", nullable = false)
	private boolean posicionesTrabajoComodas;
	
	@Column(name = "elementos_comunicacion_interna", nullable = false)
	private boolean elementosComunicacionInterna;
	
	@Column(name = "delimitacion_areas_trabajo", nullable = false)
	private boolean delimitacionAreasTrabajo;
	
	@Column(name = "seguridad_ocupacional", nullable = false)
	private boolean seguridadOcupacional;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the instalacionesAdecuadas
	 */
	public boolean isInstalacionesAdecuadas() {
		return instalacionesAdecuadas;
	}

	/**
	 * @param instalacionesAdecuadas the instalacionesAdecuadas to set
	 */
	public void setInstalacionesAdecuadas(boolean instalacionesAdecuadas) {
		this.instalacionesAdecuadas = instalacionesAdecuadas;
	}

	/**
	 * @return the luzAdecuada
	 */
	public boolean isLuzAdecuada() {
		return luzAdecuada;
	}

	/**
	 * @param luzAdecuada the luzAdecuada to set
	 */
	public void setLuzAdecuada(boolean luzAdecuada) {
		this.luzAdecuada = luzAdecuada;
	}

	/**
	 * @return the temperaturaAdecuada
	 */
	public boolean isTemperaturaAdecuada() {
		return temperaturaAdecuada;
	}

	/**
	 * @param temperaturaAdecuada the temperaturaAdecuada to set
	 */
	public void setTemperaturaAdecuada(boolean temperaturaAdecuada) {
		this.temperaturaAdecuada = temperaturaAdecuada;
	}

	/**
	 * @return the posicionesTrabajoComodas
	 */
	public boolean isPosicionesTrabajoComodas() {
		return posicionesTrabajoComodas;
	}

	/**
	 * @param posicionesTrabajoComodas the posicionesTrabajoComodas to set
	 */
	public void setPosicionesTrabajoComodas(boolean posicionesTrabajoComodas) {
		this.posicionesTrabajoComodas = posicionesTrabajoComodas;
	}

	/**
	 * @return the elementosComunicacionInterna
	 */
	public boolean isElementosComunicacionInterna() {
		return elementosComunicacionInterna;
	}

	/**
	 * @param elementosComunicacionInterna the elementosComunicacionInterna to set
	 */
	public void setElementosComunicacionInterna(boolean elementosComunicacionInterna) {
		this.elementosComunicacionInterna = elementosComunicacionInterna;
	}

	/**
	 * @return the delimitacionAreasTrabajo
	 */
	public boolean isDelimitacionAreasTrabajo() {
		return delimitacionAreasTrabajo;
	}

	/**
	 * @param delimitacionAreasTrabajo the delimitacionAreasTrabajo to set
	 */
	public void setDelimitacionAreasTrabajo(boolean delimitacionAreasTrabajo) {
		this.delimitacionAreasTrabajo = delimitacionAreasTrabajo;
	}

	/**
	 * @return the seguridadOcupacional
	 */
	public boolean isSeguridadOcupacional() {
		return seguridadOcupacional;
	}

	/**
	 * @param seguridadOcupacional the seguridadOcupacional to set
	 */
	public void setSeguridadOcupacional(boolean seguridadOcupacional) {
		this.seguridadOcupacional = seguridadOcupacional;
	}
}
