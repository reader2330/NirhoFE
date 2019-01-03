/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "experiencia_candidato")
public class ExperienciaCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    
    @Column(name = "puesto")
    private String puesto;
    
    @Column(name = "nivel")
    private int nivel;
    
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    @Column(name = "fecha_ini")
    private Date fechaIni;
    
    @Column(name = "antiguedad")
    private int antiguedad;
    
    @Column(name = "localidad")
    private String localidad;
    
    @Column(name = "area")
    private String area;

	public ExperienciaCandidato() {
		super();
	}

	public ExperienciaCandidato(long id, String puesto, int nivel, Date fechaFin, Date fechaIni, int antiguedad,
			String localidad, String area) {
		super();
		this.id = id;
		this.puesto = puesto;
		this.nivel = nivel;
		this.fechaFin = fechaFin;
		this.fechaIni = fechaIni;
		this.antiguedad = antiguedad;
		this.localidad = localidad;
		this.area = area;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "ExperienciaCandidato [id=" + id + ", puesto=" + puesto + ", nivel=" + nivel + ", fechaFin=" + fechaFin
				+ ", fechaIni=" + fechaIni + ", antiguedad=" + antiguedad + ", localidad=" + localidad + ", area="
				+ area + "]";
	}
	
}
