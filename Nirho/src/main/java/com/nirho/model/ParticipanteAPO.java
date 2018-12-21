/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "participanteAPO")
@NamedQueries({
    @NamedQuery(name = "ParticipanteAPO.findAll", query = "SELECT p FROM ParticipanteAPO p")})
public class ParticipanteAPO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@Column(name = "id")
	private Integer idParticipante;
    
    @Column(name = "id_proyecto")
	private Integer idProyecto;
    
    @Column(name = "nivel")
    private Integer nivel;
    @Column(name = "nivel_texto", nullable=true)
    private String nivelTexto;
    @Column(name = "nombres", nullable=true)
    private String nombres;
    @Column(name = "a_paterno", nullable=true)
    private String aPaterno;
    @Column(name = "a_materno", nullable=true)
    private String aMaterno;
    @Column(name = "genero", nullable=true)
    private String genero;
    @Column(name = "rfc", nullable=true)
    private String rfc;
    @Column(name = "puesto", nullable=true)
    private String puesto;
    @Column(name = "fecha_ingreso", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "antig_puesto")
    private Double antigPuesto;
    @Column(name = "nivel_escolaridad", nullable=true)
    private String nivelEscolaridad;
    @Column(name = "otros_estudios", nullable=true)
    private String otrosEstudios;
    @Column(name = "idioma", nullable=true)
    private String idioma;
    @Column(name = "nivel_idioma", nullable=true)
    private String nivelIdioma;
    @Column(name = "correo_electronico", nullable=true)
    private String correoElectronico;
    @Column(name = "sede", nullable=true)
    private String sede;
    @Column(name = "area_org", nullable=true)
    private String areaOrg;
    @Column(name = "id_part_jefe_inm")
    private Integer idPartJefeInm;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "participanteAPO")
   	private Set<ParticipanteAPOAmp> ampliaciones = new HashSet<>();;

    public ParticipanteAPO() {
		super();
	}

	public ParticipanteAPO(Integer idParticipante, Integer idProyecto, Integer nivel, String nivelTexto, String nombres,
			String aPaterno, String aMaterno, String genero, String rfc, String puesto, Date fechaIngreso,
			Double antigPuesto, String nivelEscolaridad, String otrosEstudios, String idioma, String nivelIdioma,
			String correoElectronico, String sede, String areaOrg, Integer idPartJefeInm,
			Set<ParticipanteAPOAmp> ampliaciones) {
		super();
		this.idParticipante = idParticipante;
		this.idProyecto = idProyecto;
		this.nivel = nivel;
		this.nivelTexto = nivelTexto;
		this.nombres = nombres;
		this.aPaterno = aPaterno;
		this.aMaterno = aMaterno;
		this.genero = genero;
		this.rfc = rfc;
		this.puesto = puesto;
		this.fechaIngreso = fechaIngreso;
		this.antigPuesto = antigPuesto;
		this.nivelEscolaridad = nivelEscolaridad;
		this.otrosEstudios = otrosEstudios;
		this.idioma = idioma;
		this.nivelIdioma = nivelIdioma;
		this.correoElectronico = correoElectronico;
		this.sede = sede;
		this.areaOrg = areaOrg;
		this.idPartJefeInm = idPartJefeInm;
		this.ampliaciones = ampliaciones;
	}

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
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

    public Double getAntigPuesto() {
        return antigPuesto;
    }

    public void setAntigPuesto(Double antigPuesto) {
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

    public Integer getIdPartJefeInm() {
		return idPartJefeInm;
	}

	public void setIdPartJefeInm(Integer idPartJefeInm) {
		this.idPartJefeInm = idPartJefeInm;
	}

	public Integer getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Integer idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Set<ParticipanteAPOAmp> getAmpliaciones() {
		return ampliaciones;
	}

	public void setAmpliaciones(Set<ParticipanteAPOAmp> ampliaciones) {
		this.ampliaciones = ampliaciones;
	}

	@Override
	public String toString() {
		return "ParticipanteAPO [idParticipante=" + idParticipante + ", idProyecto=" + idProyecto + ", nivel=" + nivel + ", nivelTexto="
				+ nivelTexto + ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno
				+ ", genero=" + genero + ", rfc=" + rfc + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso
				+ ", antigPuesto=" + antigPuesto + ", nivelEscolaridad=" + nivelEscolaridad + ", otrosEstudios="
				+ otrosEstudios + ", idioma=" + idioma + ", nivelIdioma=" + nivelIdioma + ", correoElectronico="
				+ correoElectronico + ", sede=" + sede + ", areaOrg=" + areaOrg + ", idPartJefeInm=" + idPartJefeInm
				+ ", ampliaciones=" + ampliaciones + "]";
	}

}
