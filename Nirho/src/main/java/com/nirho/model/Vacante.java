/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author eisten
 */
@Entity
@Table(name = "vacante")
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Lob
    @Column(name = "anios_experiencia")
    private byte[] aniosExperiencia;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "estado_vacante")
    private int estadoVacante;
    @Basic(optional = false)
    @Column(name = "giro")
    private int giro;
    @Basic(optional = false)
    @Column(name = "motivo")
    private int motivo;
    @Basic(optional = false)
    @Column(name = "nombre_vacante")
    private String nombreVacante;
    @Basic(optional = false)
    @Column(name = "num_vacantes")
    private int numVacantes;
    @Basic(optional = false)
    @Column(name = "puesto")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "puesto_cargo")
    private String puestoCargo;
    @Basic(optional = false)
    @Column(name = "puesto_reporta")
    private String puestoReporta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    @ManyToOne
    private Empresa empresa;
    
    public Vacante() {
    }

    public Vacante(Long id) {
        this.id = id;
    }

    public Vacante(Long id, byte[] aniosExperiencia, String direccion, int estadoVacante, int giro, int motivo, String nombreVacante, int numVacantes, String puesto, String puestoCargo, String puestoReporta) {
        this.id = id;
        this.aniosExperiencia = aniosExperiencia;
        this.direccion = direccion;
        this.estadoVacante = estadoVacante;
        this.giro = giro;
        this.motivo = motivo;
        this.nombreVacante = nombreVacante;
        this.numVacantes = numVacantes;
        this.puesto = puesto;
        this.puestoCargo = puestoCargo;
        this.puestoReporta = puestoReporta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(byte[] aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(int estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public int getGiro() {
        return giro;
    }

    public void setGiro(int giro) {
        this.giro = giro;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getNombreVacante() {
        return nombreVacante;
    }

    public void setNombreVacante(String nombreVacante) {
        this.nombreVacante = nombreVacante;
    }

    public int getNumVacantes() {
        return numVacantes;
    }

    public void setNumVacantes(int numVacantes) {
        this.numVacantes = numVacantes;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getPuestoCargo() {
        return puestoCargo;
    }

    public void setPuestoCargo(String puestoCargo) {
        this.puestoCargo = puestoCargo;
    }

    public String getPuestoReporta() {
        return puestoReporta;
    }

    public void setPuestoReporta(String puestoReporta) {
        this.puestoReporta = puestoReporta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", aniosExperiencia=" + Arrays.toString(aniosExperiencia) + ", direccion="
				+ direccion + ", estadoVacante=" + estadoVacante + ", giro=" + giro + ", motivo=" + motivo
				+ ", nombreVacante=" + nombreVacante + ", numVacantes=" + numVacantes + ", puesto=" + puesto
				+ ", puestoCargo=" + puestoCargo + ", puestoReporta=" + puestoReporta + ", usuario=" + usuario
				+ ", empresa=" + empresa + "]";
	}
    
}
