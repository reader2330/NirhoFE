/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "participante")
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_participante")
    private Integer idParticipante;
    @Basic(optional = false)
    @Column(name = "nivel")
    private int nivel;
    @Column(name = "nivel_texto")
    private String nivelTexto;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "a_paterno")
    private String aPaterno;
    @Column(name = "a_materno")
    private String aMaterno;
    @Column(name = "genero")
    private String genero;
    @Column(name = "rfc")
    private String rfc;
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "antig_puesto")
    private Long antigPuesto;
    @Column(name = "nivel_escolaridad")
    private String nivelEscolaridad;
    @Column(name = "otros_estudios")
    private String otrosEstudios;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "nivel_idioma")
    private String nivelIdioma;
    @Basic(optional = false)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "sede")
    private String sede;
    @Column(name = "area_org")
    private String areaOrg;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;

    public Participante() {
    }

    public Participante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Participante(Integer idParticipante, int nivel, String correoElectronico) {
        this.idParticipante = idParticipante;
        this.nivel = nivel;
        this.correoElectronico = correoElectronico;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getAntigPuesto() {
        return antigPuesto;
    }

    public void setAntigPuesto(Long antigPuesto) {
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

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

	@Override
	public String toString() {
		return "Participante [idParticipante=" + idParticipante + ", nivel=" + nivel + ", nivelTexto=" + nivelTexto
				+ ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", genero=" + genero
				+ ", rfc=" + rfc + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso + ", antigPuesto="
				+ antigPuesto + ", nivelEscolaridad=" + nivelEscolaridad + ", otrosEstudios=" + otrosEstudios
				+ ", idioma=" + idioma + ", nivelIdioma=" + nivelIdioma + ", correoElectronico=" + correoElectronico
				+ ", sede=" + sede + ", areaOrg=" + areaOrg + ", idEmpresa=" + idEmpresa + "]";
	}
    
}
