package com.nirho.dao;

import java.util.List;

import com.nirho.model.RoleModuloSubModuloAPO;

public interface RoleModuloSubModuloAPODAO extends BaseDAO<RoleModuloSubModuloAPO, Integer> {
	List<RoleModuloSubModuloAPO> obtenerSubModulos(int rol);
}
  