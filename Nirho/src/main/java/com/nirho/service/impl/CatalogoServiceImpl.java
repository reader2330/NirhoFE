package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.CatalogoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Candidato;
import com.nirho.model.Catalogo;
import com.nirho.service.CatalogoService;

@Service
public class CatalogoServiceImpl implements CatalogoService {
	public final static Logger logger = Logger.getLogger(CatalogoServiceImpl.class);
	
	@Autowired
	private CatalogoDAO dao;

	@Override
	public List<Catalogo> obtenerCatalogo(Long tipoCatalogo) throws NirhoServiceException {
		List<Catalogo> catalogoList = null;
		try {
			catalogoList = dao.findByTipoCatalogo(tipoCatalogo);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD el catalogo, causa [" + e.getMessage()+ "]");
		}	
		return catalogoList;
	}

@Override
	public List<Catalogo> getCatPaises() throws NirhoServiceException {
		List<Catalogo> cat = null;
		try {
			cat = dao.getCatPaises();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar los catalogos de paises, causa [" + e.getMessage()+ "]");
		}		
		return cat;
	}
	
	@Override
	public List<Catalogo> getCatGirosEmpresas() throws NirhoServiceException {
		List<Catalogo> cat = null;
		try {
			cat = dao.getCatGirosEmpresas();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar los catalogos de giros de empresas, causa [" + e.getMessage()+ "]");
		}		
		return cat;
	}

	public Catalogo getOne(long id) throws NirhoServiceException{
		try {
			return dao.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}	
	}
}
