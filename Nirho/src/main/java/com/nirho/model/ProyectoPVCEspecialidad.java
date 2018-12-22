/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alfredo.ca
 */
@Entity
@Table(name = "proyecto_pvc_especialidad")
public class ProyectoPVCEspecialidad implements Serializable {

    private static final long serialVersionUID = 12131232312311L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "especialidad")
	private Set<ProyectoPVCConocimiento> conocimientos = new HashSet<>();
    
	public ProyectoPVCEspecialidad() {
		super();
	}

	public ProyectoPVCEspecialidad(int id, String nombre, boolean status, Set<ProyectoPVCConocimiento> conocimientos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.status = status;
		this.conocimientos = conocimientos;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<ProyectoPVCConocimiento> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(Set<ProyectoPVCConocimiento> conocimientos) {
		this.conocimientos = conocimientos;
	}

	@Override
	public String toString() {
		return "ProyectoPVCEspecialidad [id=" + id + ", nombre=" + nombre + ", status=" + status + ", conocimientos="
				+ conocimientos + "]";
	}

}
