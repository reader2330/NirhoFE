package com.nirho.dao;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Catalogo;

public interface CatalogoDAO extends BaseDAO<Catalogo, Long> {

	List<Catalogo> findByTipoCatalogo(Long tipoCatalogo) throws NirhoServiceException;
	List<Catalogo> getCatPaises() throws NirhoServiceException;
	List<Catalogo> getCatGirosEmpresas() throws NirhoServiceException;
	
}
