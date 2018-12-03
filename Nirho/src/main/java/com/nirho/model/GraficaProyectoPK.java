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
public class GraficaProyectoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "id_proyecto")
    private int idProyecto;
    @Basic(optional = false)
    @Column(name = "area_org")
    private String areaOrg;
    @Basic(optional = false)
    @Column(name = "id_tema")
    private int idTema;

    public GraficaProyectoPK() {
    }

    public GraficaProyectoPK(int idProyecto, String areaOrg, int idTema) {
        this.idProyecto = idProyecto;
        this.areaOrg = areaOrg;
        this.idTema = idTema;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getAreaOrg() {
        return areaOrg;
    }

    public void setAreaOrg(String areaOrg) {
        this.areaOrg = areaOrg;
    }

    public int getIdTema() {
        return idTema;
    }

    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }

	@Override
	public String toString() {
		return "GaficaProyectoPK [idProyecto=" + idProyecto + ", areaOrg=" + areaOrg + ", idTema=" + idTema + "]";
	}
    
}
