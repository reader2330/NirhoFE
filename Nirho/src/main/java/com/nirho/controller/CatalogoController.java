package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Catalogo;
import com.nirho.service.CatalogoService;

@RestController
@RequestMapping( value = "/catalogo" )
public class CatalogoController {
	public final static Logger logger = Logger.getLogger(CatalogoController.class);
	
	@Autowired
	CatalogoService catalogoService;
		
	@GetMapping(value = "/pais")
	public List<Catalogo> pais() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("1"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/giro")
	public List<Catalogo> giro() throws NirhoControllerException{
		List<Catalogo> giros = new ArrayList<>();
		try {
			giros = catalogoService.obtenerCatalogo(new Long("2"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return giros;
	}
	
	@GetMapping(value = "/tipoContacto")
	public List<Catalogo> tipoContacto() throws NirhoControllerException{
		List<Catalogo> tipoContacto = new ArrayList<>();
		try {
			tipoContacto = catalogoService.obtenerCatalogo(new Long("3"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return tipoContacto;
	}
	
	@GetMapping(value = "/idioma")
	public List<Catalogo> idioma() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("4"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/nivelIdioma")
	public List<Catalogo> nivelIdioma() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("5"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/nivelConocimiento")
	public List<Catalogo> nivelConocimiento() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("6"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/tipoContactoEmpresa")
	public List<Catalogo> tipoContactoEmpresa() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("7"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/nivelActividad")
	public List<Catalogo> nivelActividad() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("8"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
	
	@GetMapping(value = "/competencia")
	public List<Catalogo> competencia() throws NirhoControllerException{
		List<Catalogo> catalogo = new ArrayList<>();
		try {
			catalogo = catalogoService.obtenerCatalogo(new Long("9"));
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el catalogo");
		}
		return catalogo;
	}
}
