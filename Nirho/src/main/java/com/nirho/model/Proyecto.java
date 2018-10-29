/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "num_empleados")
    private int numEmpleados;
    @Column(name = "sedes")
    private String sedes;
    @Basic(optional = false)
    @Column(name = "num_participantes")
    private int numParticipantes;
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_fin")
    private Date fechaFin;
    @Column(name = "dias_garantia")
    private Integer diasGarantia;
    @Column(name = "frecuencia_eval")
    private Long frecuenciaEval;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable=false, updatable=true)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Empresa idEmpresa;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id", nullable=false, updatable=true)
    @ManyToOne(optional = false,  cascade = CascadeType.ALL)
    private Contacto idContacto;
    
	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumEmpleados() {
		return numEmpleados;
	}

	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}

	public String getSedes() {
		return sedes;
	}

	public void setSedes(String sedes) {
		this.sedes = sedes;
	}

	public int getNumParticipantes() {
		return numParticipantes;
	}

	public void setNumParticipantes(int numParticipantes) {
		this.numParticipantes = numParticipantes;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getDiasGarantia() {
		return diasGarantia;
	}

	public void setDiasGarantia(Integer diasGarantia) {
		this.diasGarantia = diasGarantia;
	}

	public Long getFrecuenciaEval() {
		return frecuenciaEval;
	}

	public void setFrecuenciaEval(Long frecuenciaEval) {
		this.frecuenciaEval = frecuenciaEval;
	}

	public Empresa getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Empresa idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public Contacto getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Contacto idContacto) {
		this.idContacto = idContacto;
	}

	@Override
	public String toString() {
		return "Proyecto [idProyecto=" + idProyecto + ", nombre=" + nombre + ", numEmpleados=" + numEmpleados
				+ ", sedes=" + sedes + ", numParticipantes=" + numParticipantes + ", fechaRegistro=" + fechaRegistro
				+ ", fechaFin=" + fechaFin + ", diasGarantia=" + diasGarantia + ", frecuenciaEval=" + frecuenciaEval
				+ ", idEmpresa=" + idEmpresa + "]";
	}
	
}
