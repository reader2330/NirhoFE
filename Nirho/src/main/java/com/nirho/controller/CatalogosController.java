package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Catalogo;
import com.nirho.service.CatalogoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/catalogos" )
public class CatalogosController {

	public final static Logger logger = Logger.getLogger(CatalogosController.class);
	
	@Autowired
	CatalogoService catalogoService;
	
	@GetMapping(value = "/paises")
	public List<Catalogo> getCatPaises() throws NirhoControllerException{
		List<Catalogo> cat = new ArrayList<>();
		try {
			cat = catalogoService.getCatPaises();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los catalogos de paises");
		}
		return cat;
	}
	
	@GetMapping(value = "/girosEmpresas")
	public List<Catalogo> getCatGirosEmpresas() throws NirhoControllerException{
		List<Catalogo> cat = new ArrayList<>();
		try {
			cat = catalogoService.getCatGirosEmpresas();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener los catalogos de giros de empresas");
		}
		return cat;
	}

	
}
