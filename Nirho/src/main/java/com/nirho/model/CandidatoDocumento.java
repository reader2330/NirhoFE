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
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "candidato_documento")
public class CandidatoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    
    @Column(name = "idCandidato")
    private long idCandidato;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Lob
    @Column(name = "file")
    private byte[] file;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(long idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public CandidatoDocumento(long id, long idCandidato, String nombre, byte[] file) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.file = file;
		this.idCandidato = idCandidato;
	}

	@Override
	public String toString() {
		return "CandidatoDocumento [id=" + id + ", nombre=" + nombre + ", file=" + Arrays.toString(file) + "]";
	}
    
}
