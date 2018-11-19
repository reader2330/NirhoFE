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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "modulo")
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")})
public class Modulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_modulo")
    private Integer idModulo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "plantilla_cuestionario", joinColumns = {
        @JoinColumn(name = "id_modulo", referencedColumnName = "id_modulo")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tema", referencedColumnName = "id_tema")})
    @ManyToMany
    private List<Tema> temaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idModulo")
    private List<Proyecto> proyectoList;

    public Modulo() {
    }

    public Modulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public Modulo(Integer idModulo, String nombre) {
        this.idModulo = idModulo;
        this.nombre = nombre;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
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

    public List<Tema> getTemaList() {
        return temaList;
    }

    public void setTemaList(List<Tema> temaList) {
        this.temaList = temaList;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }
    
	@Override
	public String toString() {
		return "Modulo [idModulo=" + idModulo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", temaList="
				+ temaList + "]";
	}
        
}
