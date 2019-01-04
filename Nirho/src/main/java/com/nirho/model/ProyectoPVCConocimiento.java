/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author alfredo.ca
 */
@Entity
@Table(name = "proyecto_pvc_conocimiento")
public class ProyectoPVCConocimiento implements Serializable {

    private static final long serialVersionUID = 121312322312311L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "tipo")
    private int tipo;
    
    @Column(name = "calificacion")
    private int calificacion;

	public ProyectoPVCConocimiento() {
		super();
	}

	public ProyectoPVCConocimiento(int id, String nombre, int tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "ProyectoPVCConocimiento [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}

}
