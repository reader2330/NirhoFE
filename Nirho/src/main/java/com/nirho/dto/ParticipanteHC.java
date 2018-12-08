/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.dto;

import java.io.Serializable;

/**
 *
 * @author eisten
 */
public class ParticipanteHC implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idParticipante;
    private String nivel;
    private String nivelTexto;
    private String nombres;
    private String aPaterno;
    private String aMaterno;
    private String genero;
    private String rfc;
    private String puesto;
    private String fechaIngreso;
    private String antigPuesto;
    private String nivelEscolaridad;
    private String otrosEstudios;
    private String idioma;
    private String nivelIdioma;
    private String correoElectronico;
    private String sede;
    private String areaOrg;
    private String idJefeInmediato;
    
    public ParticipanteHC() {
    }

    public ParticipanteHC(String idParticipante) {
        this.idParticipante = idParticipante;
    }

    public ParticipanteHC(String idParticipante, String nivel, String correoElectronico) {
        this.idParticipante = idParticipante;
        this.nivel = nivel;
        this.correoElectronico = correoElectronico;
    }

    public String getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(String idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNivelTexto() {
        return nivelTexto;
    }

    public void setNivelTexto(String nivelTexto) {
        this.nivelTexto = nivelTexto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAPaterno() {
        return aPaterno;
    }

    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getAMaterno() {
        return aMaterno;
    }

    public void setAMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getAntigPuesto() {
        return antigPuesto;
    }

    public void setAntigPuesto(String antigPuesto) {
        this.antigPuesto = antigPuesto;
    }

    public String getNivelEscolaridad() {
        return nivelEscolaridad;
    }

    public void setNivelEscolaridad(String nivelEscolaridad) {
        this.nivelEscolaridad = nivelEscolaridad;
    }

    public String getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(String otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNivelIdioma() {
        return nivelIdioma;
    }

    public void setNivelIdioma(String nivelIdioma) {
        this.nivelIdioma = nivelIdioma;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getAreaOrg() {
        return areaOrg;
    }

    public void setAreaOrg(String areaOrg) {
        this.areaOrg = areaOrg;
    }

	public String getIdJefeInmediato() {
		return idJefeInmediato;
	}

	public void setIdJefeInmediato(String idJefeInmediato) {
		this.idJefeInmediato = idJefeInmediato;
	}

	@Override
	public String toString() {
		return "ParticipanteHC [idParticipante=" + idParticipante + ", nivel=" + nivel + ", nivelTexto=" + nivelTexto
				+ ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", genero=" + genero
				+ ", rfc=" + rfc + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso + ", antigPuesto="
				+ antigPuesto + ", nivelEscolaridad=" + nivelEscolaridad + ", otrosEstudios=" + otrosEstudios
				+ ", idioma=" + idioma + ", nivelIdioma=" + nivelIdioma + ", correoElectronico=" + correoElectronico
				+ ", sede=" + sede + ", areaOrg=" + areaOrg + ", idJefeInmediato=" + idJefeInmediato + "]";
	}
    	    
}
