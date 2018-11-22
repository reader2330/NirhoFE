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
import javax.persistence.Table;

/**
 *
 * @author alfredo.ca
 */
@Entity
@Table(name = "empleado_contacto")
public class EmpleadoContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = true)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = true)
    @Column(name = "email")
    private String email;
    @Basic(optional = true)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "beneficiario")
    private String beneficiario;
    @Basic(optional = true)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "tipo_contacto")
    private int tipoContacto;

    public EmpleadoContacto() {
    }

    public EmpleadoContacto(Long id) {
        this.id = id;
    }

    public EmpleadoContacto(Long id, String celular, String email, String nombre, String telefono, int tipoContacto) {
        this.id = id;
        this.celular = celular;
        this.email = email;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoContacto = tipoContacto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(int tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

	@Override
	public String toString() {
		return "Contacto [id=" + id + ", celular=" + celular + ", email=" + email + ", nombre=" + nombre + ", beneficiario="
				+ beneficiario + ", telefono=" + telefono + ", tipoContacto=" + tipoContacto + "]";
	}
	
}
