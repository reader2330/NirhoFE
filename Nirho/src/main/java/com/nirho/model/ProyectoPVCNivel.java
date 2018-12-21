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
@Table(name = "proyecto_pvc_nivel")
public class ProyectoPVCNivel implements Serializable {

    private static final long serialVersionUID = 121312312311L;
    
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
    @JoinColumn(name = "nivel")
	private Set<ProyectoPVCEspecialidad> especialidades = new HashSet<>();

	public ProyectoPVCNivel() {
		super();
	}

	public ProyectoPVCNivel(int id, String nombre, boolean status, Set<ProyectoPVCEspecialidad> especialidades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.status = status;
		this.especialidades = especialidades;
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

	public Set<ProyectoPVCEspecialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<ProyectoPVCEspecialidad> especialidades) {
		this.especialidades = especialidades;
	}
    
}
