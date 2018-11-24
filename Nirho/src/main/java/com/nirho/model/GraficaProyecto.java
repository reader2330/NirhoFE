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
@Table(name = "grafica_proyecto")
@NamedQueries({
    @NamedQuery(name = "GraficaProyecto.findAll", query = "SELECT g FROM GraficaProyecto g")})
public class GraficaProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GraficaProyectoPK gaficaProyectoPK;
    @Column(name = "num_resp_1")
    private Integer numResp1;
    @Column(name = "num_resp_2")
    private Integer numResp2;
    @Column(name = "num_resp_3")
    private Integer numResp3;
    @Column(name = "num_resp_4")
    private Integer numResp4;
    @Column(name = "num_resp_5")
    private Integer numResp5;
    @Column(name = "comentarios")
    private String comentarios;
    @JoinColumn(name = "id_tema", referencedColumnName = "id_tema", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tema tema;

    public GraficaProyecto() {
    }

    public GraficaProyecto(GraficaProyectoPK gaficaProyectoPK) {
        this.gaficaProyectoPK = gaficaProyectoPK;
    }

    public GraficaProyecto(int idProyecto, String areaOrg, int idTema) {
        this.gaficaProyectoPK = new GraficaProyectoPK(idProyecto, areaOrg, idTema);
    }

    public GraficaProyectoPK getGaficaProyectoPK() {
        return gaficaProyectoPK;
    }

    public void setGaficaProyectoPK(GraficaProyectoPK gaficaProyectoPK) {
        this.gaficaProyectoPK = gaficaProyectoPK;
    }

    public Integer getNumResp1() {
        return numResp1;
    }

    public void setNumResp1(Integer numResp1) {
        this.numResp1 = numResp1;
    }

    public Integer getNumResp2() {
        return numResp2;
    }

    public void setNumResp2(Integer numResp2) {
        this.numResp2 = numResp2;
    }

    public Integer getNumResp3() {
        return numResp3;
    }

    public void setNumResp3(Integer numResp3) {
        this.numResp3 = numResp3;
    }

    public Integer getNumResp4() {
        return numResp4;
    }

    public void setNumResp4(Integer numResp4) {
        this.numResp4 = numResp4;
    }

    public Integer getNumResp5() {
        return numResp5;
    }

    public void setNumResp5(Integer numResp5) {
        this.numResp5 = numResp5;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

	@Override
	public String toString() {
		return "GraficaProyecto [gaficaProyectoPK=" + gaficaProyectoPK + ", numResp1=" + numResp1 + ", numResp2="
				+ numResp2 + ", numResp3=" + numResp3 + ", numResp4=" + numResp4 + ", numResp5=" + numResp5
				+ ", comentarios=" + comentarios + ", tema=" + tema + "]";
	}
	
}
