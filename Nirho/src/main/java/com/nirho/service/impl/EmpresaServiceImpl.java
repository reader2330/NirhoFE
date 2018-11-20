package com.nirho.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.EmpresaDAO;
import com.nirho.dao.EntrevistadoDAO;
import com.nirho.dto.RegistroEmpresaDTO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Empresa;
import com.nirho.model.Entrevistado;
import com.nirho.model.view.VwEmpresasSolicitudCuestionario;
import com.nirho.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	public final static Logger logger = Logger.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private EntrevistadoDAO entrevistadoDAO;
	
	@Override
	public List<Empresa> obtenerEmpresasTodas() throws NirhoServiceException {
		List<Empresa> empresaList = null;
		try {
			empresaList = empresaDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las empresas, causa [" + e.getMessage()+ "]");
		}		
		return empresaList;
	}
	
	@Override
	public Empresa obtenerEmpresaPorRfc(String rfc) throws NirhoServiceException {
		Empresa empresa = null;
		try {
			List<Empresa> empresaList = empresaDAO.findByRfc(rfc);
			if(!empresaList.isEmpty()){
				empresa = empresaList.get(0);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
		return empresa;
	}

	@Override
	public void registraEmpresa(Empresa empresa) throws NirhoServiceException {
		try {
			if(empresa.getId()==0){
				empresaDAO.save(empresa);
			} else {
				empresaDAO.update(empresa);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void registrarEmpresaIRH(RegistroEmpresaDTO registroEmpresa)throws NirhoServiceException{
		try {
			if(registroEmpresa.getEmpresa().getId() == null){
				empresaDAO.save(registroEmpresa.getEmpresa());
				registroEmpresa.getEntrevistado().setRfcEmpresa(registroEmpresa.getEmpresa().getRfc());
				entrevistadoDAO.save(registroEmpresa.getEntrevistado());
			} else {
				empresaDAO.update(registroEmpresa.getEmpresa());
				registroEmpresa.getEntrevistado().setRfcEmpresa(registroEmpresa.getEmpresa().getRfc());
				entrevistadoDAO.update(registroEmpresa.getEntrevistado());
			}
			
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public Entrevistado consultarEmpresaIRH(String rfc)throws NirhoServiceException{
		Entrevistado entrevistadoEmpresa;
		try {
			entrevistadoEmpresa = entrevistadoDAO.findByRfcEmpresa(rfc);
		}catch (NoResultException e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NoResultException("No se encontraron resultados con para el RFC");
		}catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return entrevistadoEmpresa;
	}
	
	@Override
	public void eliminarEmpresa(Empresa empresa) throws NirhoServiceException {
		try {
			empresaDAO.eliminarEmpresa(empresa);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public List<VwEmpresasSolicitudCuestionario> getEmpresasSolicitadoEvaluacion() throws NirhoServiceException {
		List<VwEmpresasSolicitudCuestionario> empresas = null;
		try {
			empresas = empresaDAO.getEmpresasSolicitadoEvaluacion();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD las empresas que han solictado evaluacion, causa [" + e.getMessage()+ "]");
		}		
		return empresas;
	}

}
