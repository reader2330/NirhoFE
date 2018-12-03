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
 * @author alfredo.ca
 */
@Entity
@Table(name = "empleado_laboral")
public class EmpleadoLaboral implements Serializable {

    private static final long serialVersionUID = 121312323123L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    //Datos de nivel laboral
    
    @Column(name = "puesto")
    private int puesto;
    
    @Basic(optional = false)
    @Column(name = "nivel_laboral")
    private int nivelLaboral;
    
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    
    @Column(name = "fecha_termino")
    private Date fechaTermino;
    
    @Column(name = "antiguedad")
    private int antiguedad;
    
    @Column(name = "localidad")
    private String localidad;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "sueldo")
    private double sueldo;
    
	public EmpleadoLaboral() {
		super();
	}

	public EmpleadoLaboral(Long id) {
		super();
		this.id = id;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPuesto() {
		return puesto;
	}

	public int getNivelLaboral() {
		return nivelLaboral;
	}

	public void setNivelLaboral(int nivelLaboral) {
		this.nivelLaboral = nivelLaboral;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public int getAntiguedad() {
		return antiguedad;
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

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		return "EmpleadoLaboral [id=" + id + ", puesto=" + puesto + ", nivelLaboral=" + nivelLaboral + ", fechaInicio="
				+ fechaInicio + ", fechaTermino=" + fechaTermino + ", antiguedad=" + antiguedad + ", localidad="
				+ localidad + ", area=" + area + "]";
	}
     
}
