/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "tema_cuestionario")
@NamedQueries({
	@NamedQuery(name = "TemaCuestionario.findAll", 
			query = "SELECT t FROM TemaCuestionario t "
			+ " ORDER BY t.idTema ASC") })
public class TemaCuestionario implements Serializable {

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
    @OneToMany(cascade = CascadeType.MERGE, fetch=FetchType.LAZY, mappedBy = "tema")
    private List<PreguntaTema> preguntaTemaList;

    public TemaCuestionario() {
    }

    public TemaCuestionario(Integer idTema) {
        this.idTema = idTema;
    }

    public TemaCuestionario(Integer idTema, String nombre) {
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

    @XmlTransient
    public List<PreguntaTema> getPreguntaTemaList() {
        return preguntaTemaList;
    }

    public void setPreguntaTemaList(List<PreguntaTema> preguntaTemaList) {
        this.preguntaTemaList = preguntaTemaList;
    }

	@Override
	public String toString() {
		return "TemaCuestionario [idTema=" + idTema + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", preguntaTemaList=" + preguntaTemaList + "]";
	}
}
