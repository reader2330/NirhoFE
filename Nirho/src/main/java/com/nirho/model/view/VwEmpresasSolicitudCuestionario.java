package com.nirho.model.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VW_EMPRESAS_SOLICITUD_CUESTIONARIO")
@NamedQueries({
	@NamedQuery(name = "VwEmpresasSolicitudCuestionario.findCuestionariosActivos",
				query = "SELECT v FROM VwEmpresasSolicitudCuestionario v " 
	    	+ " ORDER BY v.idEmpresa ASC")
})
public class VwEmpresasSolicitudCuestionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_cuestionario_empresa")
	private int id;

	@Column(name = "id_tema")
	private int idTema;

	@Column(name = "nombre_tema")
	private String nombreTema;

	@Column(name = "id_empresa")
	private int idEmpresa;
			
	@Column(name = "nombre_empresa")
	private String nombreEmpresa;
	
	@Column(name = "rfc")
	private String rfc;

	@Column(name = "finalizado")
	private int finalizado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getNombreTema() {
		return nombreTema;
	}
	public void setNombreTema(String nombreTema) {
		this.nombreTema = nombreTema;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public int getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}
	@Override
	public String toString() {
		return "VwEmpresasSolicitudCuestionario [id=" + id + ", idTema=" + idTema + ", nombreTema=" + nombreTema
				+ ", idEmpresa=" + idEmpresa + ", nombreEmpresa=" + nombreEmpresa + ", rfc=" + rfc + ", finalizado="
				+ finalizado +"]";
	}
	
	
	
}
