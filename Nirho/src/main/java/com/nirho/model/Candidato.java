/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;
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

import org.apache.xmlbeans.impl.common.PushedInputStream;

@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    
    @Column(name = "idSolicitate")
    private long idSolicitante;
    
    @Column(name = "idVacante")
    private long idVacante;
    
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @Column(name = "nacimiento")
    private Date nacimiento;
    
    @Column(name = "perfil")
    private String perfil;
    
    @Column(name = "situacion")
    private String situacion;
    
    @Column(name = "pretencion")
    private String pretencion;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Basic(optional = false)
    @Column(name = "estado")
    private long estado;
    
    @Column(name = "rol")
    private String rol;
    
    @Column(name = "caracteristicas")
    private CaracteristicasCandidatoCv caracteristicasCandidatoCv;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato")
   	private Set<ContactoCandidato> contactos = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato")
   	private Set<IdiomaCandidato> idiomas = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato")
   	private Set<ConocimientoCandidato> conocimentos = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "candidato")
   	private Set<ExperienciaCandidato> puestos = new HashSet<>();
    
    public Candidato() {
    }

    public Candidato(long id) {
        this.id = id;
    }

	public Candidato(long id, long idSolicitante, String rfc, String nombre, String username, String password,
			String nacionalidad, Date nacimiento, String perfil, String situacion, String pretencion,
			String direccion, long estado, String rol, CaracteristicasCandidatoCv caracteristicasCandidatoCv,
			Set<ContactoCandidato> contactos, Set<IdiomaCandidato> idiomas, Set<ConocimientoCandidato> conocimentos) {
		super();
		this.id = id;
		this.idSolicitante = idSolicitante;
		this.rfc = rfc;
		this.nombre = nombre;
		this.username = username;
		this.password = password;
		this.nacionalidad = nacionalidad;
		this.nacimiento = nacimiento;
		this.perfil = perfil;
		this.situacion = situacion;
		this.pretencion = pretencion;
		this.direccion = direccion;
		this.estado = estado;
		this.rol = rol;
		this.caracteristicasCandidatoCv = caracteristicasCandidatoCv;
		this.contactos = contactos;
		this.idiomas = idiomas;
		this.conocimentos = conocimentos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}
	
	public long getIdVacante() {
		return idVacante;
	}

	public void setIdVacante(long idVacante) {
		this.idVacante = idVacante;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getPretencion() {
		return pretencion;
	}

	public void setPretencion(String pretencion) {
		this.pretencion = pretencion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getEstado() {
		return estado;
	}

	public void setEstado(long estado) {
		this.estado = estado;
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

	public Set<ContactoCandidato> getContactos() {
		return contactos;
	}

	public void setContactos(Set<ContactoCandidato> contactos) {
		this.contactos = contactos;
	}

	public Set<IdiomaCandidato> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Set<IdiomaCandidato> idiomas) {
		this.idiomas = idiomas;
	}

	public Set<ConocimientoCandidato> getConocimentos() {
		return conocimentos;
	}

	public void setConocimentos(Set<ConocimientoCandidato> conocimentos) {
		this.conocimentos = conocimentos;
	}

	public Set<ExperienciaCandidato> getPuestos() {
		return puestos;
	}

	public void setPuestos(Set<ExperienciaCandidato> puestos) {
		this.puestos = puestos;
	}

	@Override
	public String toString() {
		return "Candidato [id=" + id + ", idSolicitante=" + idSolicitante + ", rfc=" + rfc + ", nombre=" + nombre
				+ ", username=" + username + ", password=" + password + ", nacionalidad=" + nacionalidad
				+ ", nacimiento=" + nacimiento + ", perfil=" + perfil + ", situacion=" + situacion + ", pretencion="
				+ pretencion + ", direccion=" + direccion + ", estado=" + estado + ", rol=" + rol
				+ ", caracteristicasCandidatoCv=" + caracteristicasCandidatoCv + ", contactos=" + contactos
				+ ", idiomas=" + idiomas + ", conocimentos=" + conocimentos + "]";
	}

}
