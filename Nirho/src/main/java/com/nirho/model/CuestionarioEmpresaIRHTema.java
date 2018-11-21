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

@Entity
@Table(name = "cuestionario_empresa_irh_tema")
public class CuestionarioEmpresaIRHTema implements Serializable {

    private static final long serialVersionUID = 168L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id_tema")
    private Long id;
    @Basic(optional=false)
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name = "status")
	private boolean status;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuestionarioEmpresaTema")
   	private Set<CuestionarioEmpresaIRHPregunta> preguntas;

	public CuestionarioEmpresaIRHTema() {
		super();
	}

	public CuestionarioEmpresaIRHTema(Long id, String nombre, String descripcion, boolean status,
			Set<CuestionarioEmpresaIRHPregunta> preguntas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.status = status;
		this.preguntas = preguntas;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<CuestionarioEmpresaIRHPregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<CuestionarioEmpresaIRHPregunta> preguntas) {
		this.preguntas = preguntas;
	}
    
}
