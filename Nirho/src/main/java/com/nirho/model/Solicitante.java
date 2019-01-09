package com.nirho.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "soliciante")
public class Solicitante implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "direccion")
	private String direccion;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "giro")
	private int giro;
	@Column(name = "pais")
	private int pais;
	@Column(name = "rfc", nullable = false)
	private String rfc;
	
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<SolicitanteContacto> contactos = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<SolicitanteVacante> vacantes = new HashSet<>();
	
	public Solicitante() {
	}

	public Solicitante(String direccion, String nombre, int giro, int pais, String rfc) {
		this.direccion = direccion;
		this.nombre = nombre;
		this.giro = giro;
		this.pais = pais;
		this.rfc = rfc;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the giro
	 */
	public int getGiro() {
		return giro;
	}

	/**
	 * @param giro the giro to set
	 */
	public void setGiro(int giro) {
		this.giro = giro;
	}

	/**
	 * @return the pais
	 */
	public int getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(int pais) {
		this.pais = pais;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
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

	public Set<SolicitanteContacto> getContactos() {
		return contactos;
	}

	public void setContactos(Set<SolicitanteContacto> contactos) {
		this.contactos = contactos;
	}

	public Set<SolicitanteVacante> getVacantes() {
		return vacantes;
	}

	public void setVacantes(Set<SolicitanteVacante> vacantes) {
		this.vacantes = vacantes;
	}

	@Override
	public String toString() {
		return "Solicitante [id=" + id + ", direccion=" + direccion + ", nombre=" + nombre + ", giro=" + giro
				+ ", pais=" + pais + ", rfc=" + rfc + "]";
	}


}
