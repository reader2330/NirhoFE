package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ClbSubmodulo;

public interface RolClbService {
	List<ClbSubmodulo> obtenerSubModulos(int rol) throws NirhoServiceException;
}
