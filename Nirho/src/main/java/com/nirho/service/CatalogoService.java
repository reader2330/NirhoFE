package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Catalogo;

public interface CatalogoService {
	List<Catalogo> obtenerCatalogo(Long tipoCatalogo) throws NirhoServiceException;
	List<Catalogo> getCatPaises() throws NirhoServiceException;
	List<Catalogo> getCatGirosEmpresas() throws NirhoServiceException;
	Catalogo getOne(long id) throws NirhoServiceException;
	
}
