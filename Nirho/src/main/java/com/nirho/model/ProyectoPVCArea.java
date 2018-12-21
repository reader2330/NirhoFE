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
@Table(name = "proyecto_pvc_area")
public class ProyectoPVCArea implements Serializable {

    private static final long serialVersionUID = 112131231231L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "id_proyecto")
    private int idProyecto;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "area")
	private Set<ProyectoPVCEsfera> esferas = new HashSet<>();

	public ProyectoPVCArea() {
		super();
	}

	public ProyectoPVCArea(int id, String nombre, boolean status, Set<ProyectoPVCEsfera> esferas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.status = status;
		this.esferas = esferas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<ProyectoPVCEsfera> getEsferas() {
		return esferas;
	}

	public void setEsferas(Set<ProyectoPVCEsfera> esferas) {
		this.esferas = esferas;
	}

	@Override
	public String toString() {
		return "ProyectoPVCArea [id=" + id + ", nombre=" + nombre + ", status=" + status + ", esferas=" + esferas + "]";
	}
    
}
