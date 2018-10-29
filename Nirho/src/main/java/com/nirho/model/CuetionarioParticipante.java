/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "cuetionario_participante")
@NamedQueries({
    @NamedQuery(name = "CuetionarioParticipante.findAll", query = "SELECT c FROM CuetionarioParticipante c")})
public class CuetionarioParticipante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CuetionarioParticipantePK cuetionarioParticipantePK;
    @Column(name = "respuesta")
    private Integer respuesta;
    @Column(name = "respuesta_texto")
    private String respuestaTexto;
    @JoinColumn(name = "id_participante", referencedColumnName = "id_participante", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Participante participante;
    @JoinColumn(name = "id_pregunta", referencedColumnName = "id_pregunta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PreguntaTema preguntaTema;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TemaCuestionario temaCuestionario;

    public CuetionarioParticipante() {
    }

    public CuetionarioParticipante(CuetionarioParticipantePK cuetionarioParticipantePK) {
        this.cuetionarioParticipantePK = cuetionarioParticipantePK;
    }

    public CuetionarioParticipante(int idParticipante, int idTema, int idPregunta) {
        this.cuetionarioParticipantePK = new CuetionarioParticipantePK(idParticipante, idTema, idPregunta);
    }

    public CuetionarioParticipantePK getCuetionarioParticipantePK() {
        return cuetionarioParticipantePK;
    }

    public void setCuetionarioParticipantePK(CuetionarioParticipantePK cuetionarioParticipantePK) {
        this.cuetionarioParticipantePK = cuetionarioParticipantePK;
    }

    public Integer getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Integer respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public PreguntaTema getPreguntaTema() {
        return preguntaTema;
    }

    public void setPreguntaTema(PreguntaTema preguntaTema) {
        this.preguntaTema = preguntaTema;
    }

    public TemaCuestionario getTemaCuestionario() {
        return temaCuestionario;
    }

    public void setTemaCuestionario(TemaCuestionario temaCuestionario) {
        this.temaCuestionario = temaCuestionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuetionarioParticipantePK != null ? cuetionarioParticipantePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuetionarioParticipante)) {
            return false;
        }
        CuetionarioParticipante other = (CuetionarioParticipante) object;
        if ((this.cuetionarioParticipantePK == null && other.cuetionarioParticipantePK != null) || (this.cuetionarioParticipantePK != null && !this.cuetionarioParticipantePK.equals(other.cuetionarioParticipantePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nirho.model.CuetionarioParticipante[ cuetionarioParticipantePK=" + cuetionarioParticipantePK + " ]";
    }
    
}
