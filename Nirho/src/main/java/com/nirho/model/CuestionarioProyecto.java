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
@Table(name = "cuestionario_proyecto")
@NamedQueries({
    @NamedQuery(name = "CuestionarioProyecto.findAll", query = "SELECT c FROM CuestionarioProyecto c")})
public class CuestionarioProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuestionarioProyectoPK cuestionarioProyectoPK;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema tema;

    public CuestionarioProyecto() {
    }

    public CuestionarioProyecto(CuestionarioProyectoPK cuestionarioProyectoPK) {
        this.cuestionarioProyectoPK = cuestionarioProyectoPK;
    }

    public CuestionarioProyecto(int idProyecto, int idTema, int idPregunta) {
        this.cuestionarioProyectoPK = new CuestionarioProyectoPK(idProyecto, idTema, idPregunta);
    }

    public CuestionarioProyectoPK getCuestionarioProyectoPK() {
        return cuestionarioProyectoPK;
    }

    public void setCuestionarioProyectoPK(CuestionarioProyectoPK cuestionarioProyectoPK) {
        this.cuestionarioProyectoPK = cuestionarioProyectoPK;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
    public int hashCode() {
        int hash = 0;
        hash += (cuestionarioProyectoPK != null ? cuestionarioProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuestionarioProyecto)) {
            return false;
        }
        CuestionarioProyecto other = (CuestionarioProyecto) object;
        if ((this.cuestionarioProyectoPK == null && other.cuestionarioProyectoPK != null) || (this.cuestionarioProyectoPK != null && !this.cuestionarioProyectoPK.equals(other.cuestionarioProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nirho.model.CuestionarioProyecto[ cuestionarioProyectoPK=" + cuestionarioProyectoPK + " ]";
    }
    
}
