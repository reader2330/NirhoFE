/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "cuestionario_opcion")
@NamedQueries({
    @NamedQuery(name = "CuestionarioOpcion.findAll", query = "SELECT c FROM CuestionarioOpcion c")})
public class CuestionarioOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuestionarioOpcionPK cuestionarioOpcionPK;
    @JoinColumn(name = "id_opcion", referencedColumnName = "id_opcion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Opcion opcion;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema tema;

    public CuestionarioOpcion() {
    }

    public CuestionarioOpcion(CuestionarioOpcionPK cuestionarioOpcionPK) {
        this.cuestionarioOpcionPK = cuestionarioOpcionPK;
    }

    public CuestionarioOpcion(int idOpcion, int idTema, int idPregunta) {
        this.cuestionarioOpcionPK = new CuestionarioOpcionPK(idOpcion, idTema, idPregunta);
    }

	public CuestionarioOpcionPK getCuestionarioOpcionPK() {
		return cuestionarioOpcionPK;
	}

	public void setCuestionarioOpcionPK(CuestionarioOpcionPK cuestionarioOpcionPK) {
		this.cuestionarioOpcionPK = cuestionarioOpcionPK;
	}

	public Opcion getOpcion() {
		return opcion;
	}

	public void setOpcion(Opcion opcion) {
		this.opcion = opcion;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return "CuestionarioOpcion [cuestionarioOpcionPK=" + cuestionarioOpcionPK + ", opcion=" + opcion + ", proyecto="
				+ proyecto + ", tema=" + tema + "]";
	}
    
}
