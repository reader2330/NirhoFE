/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "evaluador_evaluado")
@NamedQueries({
    @NamedQuery(name = "EvaluadorEvaluado.findAll", query = "SELECT e FROM EvaluadorEvaluado e")})
public class EvaluadorEvaluado implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvaluadorEvaluadoPK evaluadorEvaluadoPK;

    public EvaluadorEvaluado() {
    }

    public EvaluadorEvaluado(EvaluadorEvaluadoPK evaluadorEvaluadoPK) {
        this.evaluadorEvaluadoPK = evaluadorEvaluadoPK;
    }

    public EvaluadorEvaluado(int idProyecto, int idEvaluador, int idEvaluado) {
        this.evaluadorEvaluadoPK = new EvaluadorEvaluadoPK(idProyecto, idEvaluador, idEvaluado);
    }

    public EvaluadorEvaluadoPK getEvaluadorEvaluadoPK() {
        return evaluadorEvaluadoPK;
    }

    public void setEvaluadorEvaluadoPK(EvaluadorEvaluadoPK evaluadorEvaluadoPK) {
        this.evaluadorEvaluadoPK = evaluadorEvaluadoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluadorEvaluadoPK != null ? evaluadorEvaluadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvaluadorEvaluado)) {
            return false;
        }
        EvaluadorEvaluado other = (EvaluadorEvaluado) object;
        if ((this.evaluadorEvaluadoPK == null && other.evaluadorEvaluadoPK != null) || (this.evaluadorEvaluadoPK != null && !this.evaluadorEvaluadoPK.equals(other.evaluadorEvaluadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nirho.model.EvaluadorEvaluado[ evaluadorEvaluadoPK=" + evaluadorEvaluadoPK + " ]";
    }
    
}
