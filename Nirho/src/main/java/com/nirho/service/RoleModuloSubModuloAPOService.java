package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.RoleModuloSubModuloAPO;

public interface RoleModuloSubModuloAPOService {
	List<RoleModuloSubModuloAPO> obtenerSubModulos(int rol) throws NirhoServiceException;
}
