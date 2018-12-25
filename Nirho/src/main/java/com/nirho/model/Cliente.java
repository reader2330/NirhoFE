/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
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
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 12133123123L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "telefono")
    private String telefono;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente")
   	private Set<ModulosCliente> modulos;
    
	public Cliente () {
		super();
	}

	public Cliente (Integer id, String correo, String rfc, String nombre, String telefono,
			Set<ModulosCliente> modulos) {
		super();
		this.id = id;
		this.correo = correo;
		this.rfc = rfc;
		this.nombre = nombre;
		this.telefono = telefono;
		this.modulos = modulos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<ModulosCliente> getModulos() {
		return modulos;
	}

	public void setModulos(Set<ModulosCliente> modulos) {
		this.modulos = modulos;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", correo=" + correo + ", rfc=" + rfc
				+ ", nombre=" + nombre + ", telefono=" + telefono + ", modulos=" + modulos
				+ "]";
	}

}
