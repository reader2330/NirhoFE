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
@Table(name = "cuestionario_proyecto_tp_tema")
public class CuestionarioProyectoTPTema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional=false)
    @Column(name="id_tema")
    private Integer idTema;
    @Basic(optional=false)
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="status")
    private boolean status;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuestionarioProyectoTPTema")
   	private Set<CuestionarioProyectoTPPregunta> preguntas;
    
    public CuestionarioProyectoTPTema() {
    }

    public CuestionarioProyectoTPTema(Integer idTema) {
        this.idTema = idTema;
    }

    public CuestionarioProyectoTPTema(Integer idTema, String nombre) {
        this.idTema = idTema;
        this.nombre = nombre;
    }

    public Integer getIdTema() {
        return idTema;
    }

    public void setIdTema(Integer idTema) {
        this.idTema = idTema;
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

	public Set<CuestionarioProyectoTPPregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<CuestionarioProyectoTPPregunta> preguntas) {
		this.preguntas = preguntas;
	}

	@Override
	public String toString() {
		return "TemaCuestionario [idTema=" + idTema + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	}
}
