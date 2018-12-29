/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nirho.dto;

import java.util.HashSet;
import java.util.Set;

import com.nirho.model.ProyectoPVCEspecialidad;


public class ProyectoPVCNivelDTO implements Comparable {

    private int id;
    
    private String nombre;
    
    private boolean status;

	private Set<ProyectoPVCEspecialidad> especialidades = new HashSet<>();

	public ProyectoPVCNivelDTO() {
	}

	public ProyectoPVCNivelDTO(int id, String nombre, boolean status, Set<ProyectoPVCEspecialidad> especialidades) {
		this.id = id;
		this.nombre = nombre;
		this.status = status;
		this.especialidades = especialidades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<ProyectoPVCEspecialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<ProyectoPVCEspecialidad> especialidades) {
		this.especialidades = especialidades;
	}
	
	@Override
	public int compareTo(Object arg0) {
		String[] thisValue = nombre.split(" ");
		String[] other = ((ProyectoPVCNivelDTO)arg0).getNombre().split(" ");
		return Integer.valueOf(thisValue[0]).compareTo(Integer.valueOf(other[0]));
	}
    
}
