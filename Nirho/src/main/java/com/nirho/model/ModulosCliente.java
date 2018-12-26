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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "modulos_cliente")
@NamedQueries({
    @NamedQuery(name = "ModulosCliente.findAll", query = "SELECT m FROM ModulosCliente m")})
public class ModulosCliente implements Serializable {

    private static final long serialVersionUID = 71L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "check_modulo")
    private boolean checkModulo;

	public ModulosCliente() {
		super();
	}

	public ModulosCliente(Long id, String nombre, boolean checkModulo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.checkModulo = checkModulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isCheckModulo() {
		return checkModulo;
	}

	public void setCheckModulo(boolean checkModulo) {
		this.checkModulo = checkModulo;
	}

	@Override
	public String toString() {
		return "ModulosCliente [id=" + id + ", nombre=" + nombre + ", checkModulo=" + checkModulo + "]";
	}

	
}
