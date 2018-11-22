package com.nirho.model.view;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VW_TEMA_CUESTIONARIO")
@NamedQueries({
	@NamedQuery(name = "VwTemaCuestionario.findAll", 
			query = "SELECT t FROM VwTemaCuestionario t "
			+ " ORDER BY t.idTema ASC") })
public class VwTemaCuestionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tema")
    private int idTema;
    
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;

    public VwTemaCuestionario() {
    }

    public VwTemaCuestionario(int idTema) {
        this.idTema = idTema;
    }

    public VwTemaCuestionario(int idTema, String nombre) {
        this.idTema = idTema;
        this.nombre = nombre;
    }

    public int getIdTema() {
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

	@Override
	public String toString() {
		return "TemaCuestionario [idTema=" + idTema + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	}
       
}
