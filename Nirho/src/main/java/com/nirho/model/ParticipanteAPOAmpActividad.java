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
@Table(name = "participanteAPOAmpActividad")
@NamedQueries({
    @NamedQuery(name = "ParticipanteAPOAmpActividad.findAll", query = "SELECT p FROM ParticipanteAPOAmpActividad p")})
public class ParticipanteAPOAmpActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    
    @Column(name = "nombre", nullable=true)
    private String nombre;

    @Column(name = "fecha_creacion", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @Column(name = "fecha_vencimiento", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    
    @Column(name = "fecha_termino", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date fechaTermino;
    
    @Column(name = "calificacion")
    private Integer calificacion;
    
    @Basic(optional = false)
    @Column(name = "status")
    private Boolean status;

	public ParticipanteAPOAmpActividad() {
		super();
	}

	public ParticipanteAPOAmpActividad(Integer id, String nombre, Date fechaCreacion, Date fechaVencimiento,
			Date fechaTermino, Integer calificacion, Boolean status) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaTermino = fechaTermino;
		this.calificacion = calificacion;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Date getFechaTermino() {
		return fechaTermino;
	}

	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Integer getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ParticipanteAPOAmpActividades [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion
				+ ", fechaVencimiento=" + fechaVencimiento + ", fechaTermino=" + fechaTermino + ", calificacion="
				+ calificacion + ", status=" + status + "]";
	}
    
}
