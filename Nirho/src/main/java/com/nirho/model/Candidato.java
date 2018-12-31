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

@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "idSolicitate")
    private Long idSolicitante;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "disponibilidad")
    private String disponibilidad;
    @Basic(optional = false)
    @Column(name = "estado")
    private long estado;
    @Column(name = "nacimiento")
    private String nacimiento;
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "perfil")
    private String perfil;
    @Column(name = "pretencion")
    private String pretencion;
    @Basic(optional = false)
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "situacion")
    private String situacion;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;
    @Column(name = "caracteristicas")
    private CaracteristicasCandidatoCv caracteristicasCandidatoCv;
    
    public Candidato() {
    }

    public Candidato(Long id) {
        this.id = id;
    }

    public Candidato(Long id, long estado, String rfc) {
        this.id = id;
        this.estado = estado;
        this.rfc = rfc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public Long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public long getEstado() {
        return estado;
    }

    public void setEstado(long estado) {
        this.estado = estado;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getPretencion() {
        return pretencion;
    }

    public void setPretencion(String pretencion) {
        this.pretencion = pretencion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public CaracteristicasCandidatoCv getCaracteristicasCandidatoCv() {
		return caracteristicasCandidatoCv;
	}

	public void setCaracteristicasCandidatoCv(CaracteristicasCandidatoCv caracteristicasCandidatoCv) {
		this.caracteristicasCandidatoCv = caracteristicasCandidatoCv;
	}

	@Override
	public String toString() {
		return "Candidato [id=" + id + ", direccion=" + direccion + ", disponibilidad=" + disponibilidad + ", estado="
				+ estado + ", nacimiento=" + nacimiento + ", nacionalidad=" + nacionalidad + ", nombre=" + nombre
				+ ", perfil=" + perfil + ", pretencion=" + pretencion + ", rfc=" + rfc + ", situacion=" + situacion
				+ "]";
	}
    
}
