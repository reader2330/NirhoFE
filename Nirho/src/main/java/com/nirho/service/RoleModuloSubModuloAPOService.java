package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;

public interface RoleModuloSubModuloAPOService {
	List<Integer> obtenerSubModulos(int rol) throws NirhoServiceException;
}
