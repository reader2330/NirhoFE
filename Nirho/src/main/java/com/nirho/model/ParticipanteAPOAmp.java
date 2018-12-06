/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "participanteAPOAmp")
@NamedQueries({
    @NamedQuery(name = "ParticipanteAPOAmp.findAll", query = "SELECT p FROM ParticipanteAPOAmp p")})
public class ParticipanteAPOAmp implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "id_participante")
    private Integer idParticipante;
    
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
    
    public ParticipanteAPOAmp() {
		super();
	}

	
	public ParticipanteAPOAmp(Integer id, Integer idParticipante, String objetivoPuesto, String funciones,
			String actividades, String metaKpi, String cantidadMeta, String unidadMedida, String tiempo,
			String frecuenciaEval, Integer idEvaluador) {
		super();
		this.id = id;
		this.idParticipante = idParticipante;
		this.objetivoPuesto = objetivoPuesto;
		this.funciones = funciones;
		this.actividades = actividades;
		this.metaKpi = metaKpi;
		this.cantidadMeta = cantidadMeta;
		this.unidadMedida = unidadMedida;
		this.tiempo = tiempo;
		this.frecuenciaEval = frecuenciaEval;
		this.idEvaluador = idEvaluador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Integer idParticipante) {
		this.idParticipante = idParticipante;
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

	@Override
	public String toString() {
		return "ParticipanteAPOAmp [id=" + id + ", objetivoPuesto=" + objetivoPuesto + ", funciones=" + funciones
				+ ", actividades=" + actividades + ", metaKpi=" + metaKpi + ", cantidadMeta=" + cantidadMeta
				+ ", unidadMedida=" + unidadMedida + ", tiempo=" + tiempo + ", frecuenciaEval=" + frecuenciaEval
				+ ", idEvaluador=" + idEvaluador + "]";
	}
    
}
