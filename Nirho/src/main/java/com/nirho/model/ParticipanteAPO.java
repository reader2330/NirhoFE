/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "id_proyecto")
	private Integer idProyecto;
    
    @Column(name = "nivel")
    private Integer nivel;
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
    private Double antigPuesto;
    @Column(name = "nivel_escolaridad")
    private String nivelEscolaridad;
    @Column(name = "otros_estudios")
    private String otrosEstudios;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "nivel_idioma")
    private String nivelIdioma;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "sede")
    private String sede;
    @Column(name = "area_org")
    private String areaOrg;
    @Column(name = "id_part_jefe_inm")
    private Integer idPartJefeInm;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "participanteAPO")
   	private Set<ParticipanteAPOAmp> ampliaciones;
    
    public ParticipanteAPO() {
		super();
	}

	public ParticipanteAPO(Integer id, Integer idProyecto, Integer nivel, String nivelTexto, String nombres,
			String aPaterno, String aMaterno, String genero, String rfc, String puesto, Date fechaIngreso,
			Double antigPuesto, String nivelEscolaridad, String otrosEstudios, String idioma, String nivelIdioma,
			String correoElectronico, String sede, String areaOrg, Integer idPartJefeInm,
			Set<ParticipanteAPOAmp> ampliaciones) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "ParticipanteAPO [id=" + id + ", idProyecto=" + idProyecto + ", nivel=" + nivel + ", nivelTexto="
				+ nivelTexto + ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno
				+ ", genero=" + genero + ", rfc=" + rfc + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso
				+ ", antigPuesto=" + antigPuesto + ", nivelEscolaridad=" + nivelEscolaridad + ", otrosEstudios="
				+ otrosEstudios + ", idioma=" + idioma + ", nivelIdioma=" + nivelIdioma + ", correoElectronico="
				+ correoElectronico + ", sede=" + sede + ", areaOrg=" + areaOrg + ", idPartJefeInm=" + idPartJefeInm
				+ ", ampliaciones=" + ampliaciones + "]";
	}

}
