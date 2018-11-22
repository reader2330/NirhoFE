package com.nirho.dao;

import java.util.List;

public interface BaseDAO<T, Id> {
	List<T> findAll();
	T getOne(Id id);
	void save(T t);
	void update(T t);
	boolean delete(T entidad);
}
