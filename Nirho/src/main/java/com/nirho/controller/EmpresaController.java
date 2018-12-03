package com.nirho.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nirho.dto.DetalleEmpresaDTO;
import com.nirho.dto.RegistroEmpresaDTO;
import com.nirho.exception.NirhoControllerException;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;
import com.nirho.service.EmpresaService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( value = "/empresa" )
public class EmpresaController {

	public final static Logger logger = Logger.getLogger(EmpresaController.class);
	
	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(value = "/todas")
	public List<Empresa> todas() throws NirhoControllerException{
		List<Empresa> empresa = new ArrayList<>();
		try {
			empresa = empresaService.obtenerEmpresasTodas();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return empresa;
	}
	
	@GetMapping(value = "/porRfc")
	public Empresa porRfc(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		Empresa empresa = new Empresa();
		try {
			empresa = empresaService.obtenerEmpresaPorRfc(rfc);
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return empresa;
	}

	@PostMapping(value = "/registrar")
	public void registrarEmpresa(@Valid @RequestBody Empresa empresa) throws NirhoControllerException {
		logger.info(" ********************************* empresa a insertar/actualizar [" + empresa + "] *****************************");
		try {
			empresaService.registraEmpresa(empresa);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar la empresa en la BD");
		}
	}
	
	@RequestMapping(value = "/eliminar", method = RequestMethod.POST)
	@ResponseBody
	public void eliminarEmpresa(@RequestBody Empresa empresa) throws NirhoControllerException {
		logger.info(" ********************************* empresa a eliminar [" + empresa + "] *****************************");
		try {
			empresaService.eliminarEmpresa(empresa);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al eliminar la empresa en la BD");
		}
	}
	
	@PostMapping(value = "/registrarEmpresaIRH")
	public void registrarEmpresaIRH(@RequestBody RegistroEmpresaDTO registroEmpresaDTO) throws NirhoControllerException {
		try {
			empresaService.registrarEmpresaIRH(registroEmpresaDTO);
		} catch (NirhoServiceException e) {
			throw new NirhoControllerException("Problemas al registrar la empresa IRH en la BD");
		}
	}
	
	@GetMapping(value = "/consultarEmpresaIRHRfc")
	public DetalleEmpresaDTO consultarEmpresaIRHRfc(@RequestParam(name="rfc") String rfc) throws NirhoControllerException{
		DetalleEmpresaDTO detEmpresa = new DetalleEmpresaDTO();
		Entrevistado entrevistadoEmpresa = new Entrevistado();
		try {
			entrevistadoEmpresa = empresaService.consultarEmpresaIRH(rfc);
			detEmpresa.setEntrevistado(entrevistadoEmpresa);
			detEmpresa.setEmpresa(entrevistadoEmpresa.getEmpresa());
		}catch (NoResultException e) {
			throw new NoResultException("No se encontraron resultados con para el RFC");
		}catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener el registro de la empresa");
		}
		return detEmpresa;
	}


	@GetMapping(value = "/solicitadoEvaluacion")
	public List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoControllerException{
		List<VwEmpresasSolicitudCuestionario> empresas = new ArrayList<>();
		try {
			empresas = empresaService.getEmpresasSolicitadoEvaluacion();
		} catch(NirhoServiceException e){
			throw new NirhoControllerException("Problemas al obtener empresas que han solicitado evaluacion");
		}
		return empresas;
	}
	
}
