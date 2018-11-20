/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "ENTREVISTADO")
public class Entrevistado implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2813778457536794654L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
	
    @Column(name = "nombre_responsable_llenado", nullable = false)
    private String nombreResponsableLlenado;
    
    @Column(name = "puesto_responsable_llenado", nullable = false)
    private String puestoResponsableLlenado;
    
    @Column(name = "nombre_entrevistador", nullable = false)
    private String nombreEntrevistador;
    
    @Column(name = "nombre_entrevistado", nullable = false)
    private String nombreEntrevistado;
    
    @Column(name = "puesto_entrevistado", nullable = false)
    private String puestoEntrevistado;
    
    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;
    
    @Column(name = "telefono_celular", nullable = false)
    private Long telefonoCelular;
    
    @Column(name = "telefono_oficina_extension", nullable = false)
    private Long telefono_oficina_extension;
    
    @Column(name = "rfc_empresa")
    private String rfcEmpresa;

    @JoinColumn(name = "rfc_empresa", referencedColumnName = "rfc", insertable = false, updatable = false)
    @OneToOne
    private Empresa empresa;
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombreResponsableLlenado
	 */
	public String getNombreResponsableLlenado() {
		return nombreResponsableLlenado;
	}

	/**
	 * @param nombreResponsableLlenado the nombreResponsableLlenado to set
	 */
	public void setNombreResponsableLlenado(String nombreResponsableLlenado) {
		this.nombreResponsableLlenado = nombreResponsableLlenado;
	}

	/**
	 * @return the puestoResponsableLlenado
	 */
	public String getPuestoResponsableLlenado() {
		return puestoResponsableLlenado;
	}

	/**
	 * @param puestoResponsableLlenado the puestoResponsableLlenado to set
	 */
	public void setPuestoResponsableLlenado(String puestoResponsableLlenado) {
		this.puestoResponsableLlenado = puestoResponsableLlenado;
	}

	/**
	 * @return the nombreEntrevistador
	 */
	public String getNombreEntrevistador() {
		return nombreEntrevistador;
	}

	/**
	 * @param nombreEntrevistador the nombreEntrevistador to set
	 */
	public void setNombreEntrevistador(String nombreEntrevistador) {
		this.nombreEntrevistador = nombreEntrevistador;
	}

	/**
	 * @return the nombreEntrevistado
	 */
	public String getNombreEntrevistado() {
		return nombreEntrevistado;
	}

	/**
	 * @param nombreEntrevistado the nombreEntrevistado to set
	 */
	public void setNombreEntrevistado(String nombreEntrevistado) {
		this.nombreEntrevistado = nombreEntrevistado;
	}

	/**
	 * @return the puestoEntrevistado
	 */
	public String getPuestoEntrevistado() {
		return puestoEntrevistado;
	}

	/**
	 * @param puestoEntrevistado the puestoEntrevistado to set
	 */
	public void setPuestoEntrevistado(String puestoEntrevistado) {
		this.puestoEntrevistado = puestoEntrevistado;
	}

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the telefonoCelular
	 */
	public Long getTelefonoCelular() {
		return telefonoCelular;
	}

	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(Long telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	/**
	 * @return the telefono_oficina_extension
	 */
	public Long getTelefono_oficina_extension() {
		return telefono_oficina_extension;
	}

	/**
	 * @param telefono_oficina_extension the telefono_oficina_extension to set
	 */
	public void setTelefono_oficina_extension(Long telefono_oficina_extension) {
		this.telefono_oficina_extension = telefono_oficina_extension;
	}

	/**
	 * @return the rfcEmpresa
	 */
	public String getRfcEmpresa() {
		return rfcEmpresa;
	}

	/**
	 * @param rfcEmpresa the rfcEmpresa to set
	 */
	public void setRfcEmpresa(String rfcEmpresa) {
		this.rfcEmpresa = rfcEmpresa;
	}

	/**
	 * @return the empresa
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa the empresa to set
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
