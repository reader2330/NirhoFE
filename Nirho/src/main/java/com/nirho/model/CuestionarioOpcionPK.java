/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author eisten
 */
@Embeddable
public class CuestionarioOpcionPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @Column(name = "id_tema")
    private int idTema;
    @Basic(optional = false)
    @Column(name = "id_opcion")
    private int idOpcion;

    public CuestionarioOpcionPK() {
    }

    public CuestionarioOpcionPK(int idProyecto, int idTema, int idOpcion) {
        this.idProyecto = idProyecto;
        this.idTema = idTema;
        this.idOpcion = idOpcion;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}

	@Override
	public String toString() {
		return "CuestionarioOpcionPK [idProyecto=" + idProyecto + ", idTema=" + idTema + ", idOpcion=" + idOpcion + "]";
	}
    
}
