/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.nirho.model.ActividadesPuestoVacante;
import com.nirho.model.Candidato;
import com.nirho.model.CaracteristicasCandidatoVacante;
import com.nirho.model.CompetenciasVacante;
import com.nirho.model.ConocimientoVacante;

public class SolicitanteVacanteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private long id;
    private int aniosExperiencia;
    private int estadoVacante;
    private int giro;
    private int motivo;
    private int status;
    private String nombreVacante;
    private int numVacantes; 
    private String puesto;
    private String puestoCargo;
    private String puestoReporta;
    private List<Candidato> candidatos = new ArrayList<>();
   	private Set<ActividadesPuestoVacante> actividades = new HashSet<>();
   	private Set<CaracteristicasCandidatoVacante> caracteristicas = new HashSet<>();
   	private Set<CompetenciasVacante> competencias = new HashSet<>();
   	private Set<ConocimientoVacante> conocimientos = new HashSet<>();
    
    public SolicitanteVacanteDTO() {
    }

    public SolicitanteVacanteDTO(long id) {
        this.id = id;
    }

	public SolicitanteVacanteDTO(long id, int aniosExperiencia, int estadoVacante, int giro, int motivo, int status,
			String nombreVacante, int numVacantes, String puesto, String puestoCargo, String puestoReporta,
			List<Candidato> candidatos, Set<ActividadesPuestoVacante> actividades,
			Set<CaracteristicasCandidatoVacante> caracteristicas, Set<CompetenciasVacante> competencias,
			Set<ConocimientoVacante> conocimientos) {
		super();
		this.id = id;
		this.aniosExperiencia = aniosExperiencia;
		this.estadoVacante = estadoVacante;
		this.giro = giro;
		this.motivo = motivo;
		this.status = status;
		this.nombreVacante = nombreVacante;
		this.numVacantes = numVacantes;
		this.puesto = puesto;
		this.puestoCargo = puestoCargo;
		this.puestoReporta = puestoReporta;
		this.candidatos = candidatos;
		this.actividades = actividades;
		this.caracteristicas = caracteristicas;
		this.competencias = competencias;
		this.conocimientos = conocimientos;
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

    public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public List<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<Candidato> candidatos) {
		this.candidatos = candidatos;
	}

	public Set<ActividadesPuestoVacante> getActividades() {
		return actividades;
	}

	public void setActividades(Set<ActividadesPuestoVacante> actividades) {
		this.actividades = actividades;
	}

	public Set<CaracteristicasCandidatoVacante> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(Set<CaracteristicasCandidatoVacante> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public Set<CompetenciasVacante> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(Set<CompetenciasVacante> competencias) {
		this.competencias = competencias;
	}

	public Set<ConocimientoVacante> getConocimientos() {
		return conocimientos;
	}

	public void setConocimientos(Set<ConocimientoVacante> conocimientos) {
		this.conocimientos = conocimientos;
	}

	@Override
	public String toString() {
		return "SolicitanteVacante [id=" + id + ", aniosExperiencia=" + aniosExperiencia + ", estadoVacante="
				+ estadoVacante + ", giro=" + giro + ", motivo=" + motivo + ", nombreVacante=" + nombreVacante
				+ ", numVacantes=" + numVacantes + ", puesto=" + puesto + ", puestoCargo=" + puestoCargo
				+ ", puestoReporta=" + puestoReporta + "]";
	}


}
