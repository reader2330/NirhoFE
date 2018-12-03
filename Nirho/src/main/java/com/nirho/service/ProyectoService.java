package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.Proyecto;

public interface ProyectoService {
	List<Proyecto> obtenerProyectosTodos(Integer idModulo) throws NirhoServiceException;
	List<Proyecto> obtenerProyectosConsultor(Integer idUsuario, Integer idModulo) throws NirhoServiceException;
	Proyecto obtenerProyectoPorId(Integer idProyecto) throws NirhoServiceException;
	void registrarProyecto(Proyecto proyecto, Integer idModulo) throws NirhoServiceException;
	void asignarConsultor(ConsultorProyectoPK consultorProyectoPK) throws NirhoServiceException;
}
