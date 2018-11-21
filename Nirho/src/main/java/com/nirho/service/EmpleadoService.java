package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empleado;

public interface EmpleadoService {
	
	public void addEmpleado(Empleado e) throws NirhoServiceException;
	public void updateEmpleado(Empleado e) throws NirhoServiceException;
	public List<Empleado> listEmpleados() throws NirhoServiceException;
	public Empleado getEmpleadoById(long id) throws NirhoServiceException;
	public void removeEmpleado(Empleado e) throws NirhoServiceException;
	
}
