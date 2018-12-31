/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "solicitante_vacante")
public class SolicitanteVacante implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    
    @Basic(optional = false)
    @Column(name = "anios_experiencia")
    private int aniosExperiencia;
    
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<ActividadesPuestoVacante> actividades = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<CaracteristicasCandidatoVacante> caracteristicas = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<CompetenciasVacante> competencias = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "solicitante")
   	private Set<ConocimientoVacante> conocimientos = new HashSet<>();
    
    public SolicitanteVacante() {
    }

    public SolicitanteVacante(long id) {
        this.id = id;
    }

    public SolicitanteVacante(long id, int aniosExperiencia, int estadoVacante, int giro, int motivo,
			String nombreVacante, int numVacantes, String puesto, String puestoCargo, String puestoReporta) {
		super();
		this.id = id;
		this.aniosExperiencia = aniosExperiencia;
		this.estadoVacante = estadoVacante;
		this.giro = giro;
		this.motivo = motivo;
		this.nombreVacante = nombreVacante;
		this.numVacantes = numVacantes;
		this.puesto = puesto;
		this.puestoCargo = puestoCargo;
		this.puestoReporta = puestoReporta;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

	@Override
	public String toString() {
		return "SolicitanteVacante [id=" + id + ", aniosExperiencia=" + aniosExperiencia + ", estadoVacante="
				+ estadoVacante + ", giro=" + giro + ", motivo=" + motivo + ", nombreVacante=" + nombreVacante
				+ ", numVacantes=" + numVacantes + ", puesto=" + puesto + ", puestoCargo=" + puestoCargo
				+ ", puestoReporta=" + puestoReporta + "]";
	}


}
