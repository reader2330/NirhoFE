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
public class CuestionarioProyectoPK implements Serializable {

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
    @Column(name = "id_pregunta")
    private int idPregunta;

    public CuestionarioProyectoPK() {
    }

    public CuestionarioProyectoPK(int idProyecto, int idTema, int idPregunta) {
        this.idProyecto = idProyecto;
        this.idTema = idTema;
        this.idPregunta = idPregunta;
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

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProyecto;
        hash += (int) idTema;
        hash += (int) idPregunta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuestionarioProyectoPK)) {
            return false;
        }
        CuestionarioProyectoPK other = (CuestionarioProyectoPK) object;
        if (this.idProyecto != other.idProyecto) {
            return false;
        }
        if (this.idTema != other.idTema) {
            return false;
        }
        if (this.idPregunta != other.idPregunta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nirho.model.CuestionarioProyectoPK[ idProyecto=" + idProyecto + ", idTema=" + idTema + ", idPregunta=" + idPregunta + " ]";
    }
    
}
