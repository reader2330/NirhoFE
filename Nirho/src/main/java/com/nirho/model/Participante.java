/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "participante")
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipantePK participantePK;
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
    @Column(name = "token")
    private String token;
    @Column(name = "objetivo_puesto")
    private String objetivoPuesto;
    @Column(name = "funciones")
    private String funciones;
    @Column(name = "actividades")
    private String actividades;
    @Column(name = "meta_kpi")
    private String metaKpi;
    @Column(name = "cantidad_meta")
    private String cantidadMeta;
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Column(name = "tiempo")
    private String tiempo;
    @Column(name = "frecuencia_eval")
    private String frecuenciaEval;
    @Column(name = "id_evaluador")
    private Integer idEvaluador;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    
    public Participante() {
    }

    public Participante(ParticipantePK participantePK) {
        this.participantePK = participantePK;
    }

    public Participante(int idParticipante, int idProyecto) {
        this.participantePK = new ParticipantePK(idParticipante, idProyecto);
    }

    public ParticipantePK getParticipantePK() {
        return participantePK;
    }

    public void setParticipantePK(ParticipantePK participantePK) {
        this.participantePK = participantePK;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getObjetivoPuesto() {
        return objetivoPuesto;
    }

    public void setObjetivoPuesto(String objetivoPuesto) {
        this.objetivoPuesto = objetivoPuesto;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getMetaKpi() {
        return metaKpi;
    }

    public void setMetaKpi(String metaKpi) {
        this.metaKpi = metaKpi;
    }

    public String getCantidadMeta() {
        return cantidadMeta;
    }

    public void setCantidadMeta(String cantidadMeta) {
        this.cantidadMeta = cantidadMeta;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getFrecuenciaEval() {
        return frecuenciaEval;
    }

    public void setFrecuenciaEval(String frecuenciaEval) {
        this.frecuenciaEval = frecuenciaEval;
    }

    public Integer getIdEvaluador() {
        return idEvaluador;
    }

    public void setIdEvaluador(Integer idEvaluador) {
        this.idEvaluador = idEvaluador;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

	@Override
	public String toString() {
		return "Participante [participantePK=" + participantePK + ", nivel=" + nivel + ", nivelTexto=" + nivelTexto
				+ ", nombres=" + nombres + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", genero=" + genero
				+ ", rfc=" + rfc + ", puesto=" + puesto + ", fechaIngreso=" + fechaIngreso + ", antigPuesto="
				+ antigPuesto + ", nivelEscolaridad=" + nivelEscolaridad + ", otrosEstudios=" + otrosEstudios
				+ ", idioma=" + idioma + ", nivelIdioma=" + nivelIdioma + ", correoElectronico=" + correoElectronico
				+ ", sede=" + sede + ", areaOrg=" + areaOrg + ", token=" + token + ", objetivoPuesto=" + objetivoPuesto
				+ ", funciones=" + funciones + ", actividades=" + actividades + ", metaKpi=" + metaKpi
				+ ", cantidadMeta=" + cantidadMeta + ", unidadMedida=" + unidadMedida + ", tiempo=" + tiempo
				+ ", frecuenciaEval=" + frecuenciaEval + ", idEvaluador=" + idEvaluador + ", proyecto=" + proyecto
				+ "]";
	}
	
}
