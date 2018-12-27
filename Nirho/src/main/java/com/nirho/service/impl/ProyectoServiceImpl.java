package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.constant.ProyectoConstants;
import com.nirho.dao.ConsultorProyectoDAO;
import com.nirho.dao.ModuloDAO;
import com.nirho.dao.ProyectoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ConsultorProyecto;
import com.nirho.model.ConsultorProyectoPK;
import com.nirho.model.Proyecto;
import com.nirho.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService {
	public final static Logger logger = Logger.getLogger(ProyectoServiceImpl.class);
	@Autowired
	private ProyectoDAO dao;
	@Autowired
	private ModuloDAO moduloDAO;
	@Autowired
	private ProyectoDAO proyectoDAO;
	@Autowired 
	private ConsultorProyectoDAO consulProyDAO;
	
	@Override
	public List<Proyecto> obtenerProyectosTodos(Integer idModulo) throws NirhoServiceException {
		List<Proyecto> lista = null;
		try {
			lista = moduloDAO.getOne(idModulo).getProyectoList();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return lista;
	}
	
	@Override
	public List<Proyecto> obtenerProyectosConsultor(Integer idUsuario, Integer idModulo) throws NirhoServiceException {
		List<Proyecto> lista = new ArrayList<>();
		try {
			List<ConsultorProyecto> listaCP = consulProyDAO.findByIdUsuario(idUsuario);
			for(ConsultorProyecto cp: listaCP) {
				if(cp.getProyecto().getIdModulo().intValue() == idModulo.intValue()) {
					Proyecto proyecto = dao.getOne(cp.getConsultorProyectoPK().getIdProyecto());
					if(proyecto.getIdEstatus().getIdEstatus().intValue() != ProyectoConstants.ESTATUS_FINALIZADO.intValue()) {
						lista.add(proyecto);
					}
				}
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return lista;
	}
	
	@Override
	public List<Proyecto> obtenerProyectosConsultor(Integer idUsuario) throws NirhoServiceException {
		List<Proyecto> lista = new ArrayList<>();
		try {
			if(idUsuario == 0) {
				lista = proyectoDAO.findAll();
			} else {
				List<ConsultorProyecto> listaCP = consulProyDAO.findByIdUsuario(idUsuario);
				for(ConsultorProyecto cp: listaCP) {
					Proyecto proyecto = dao.getOne(cp.getConsultorProyectoPK().getIdProyecto());
					lista.add(proyecto);
				}
			}
			
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los proyectos, causa [" + e.getMessage()+ "]");
		}		
		return lista;
	}
	
	@Override
	public Proyecto obtenerProyectoPorId(Integer idProyecto) throws NirhoServiceException {
		Proyecto proyecto = null;
		try {
			proyecto = dao.getOne(idProyecto);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD, causa [" + e.getMessage()+ "]");
		}		
		return proyecto;
	}

	@Override
	public void registrarProyecto(Proyecto proyecto, Integer idModulo) throws NirhoServiceException {
		try {
			proyecto.setIdModulo(idModulo);
			if(proyecto.getIdProyecto()==null){
				dao.save(proyecto);
			} else {
				dao.update(proyecto);
			}
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}

	@Override
	public void asignarConsultor(ConsultorProyectoPK consultorProyectoPK) throws NirhoServiceException {
		try {
			ConsultorProyecto consultorProyecto = new ConsultorProyecto();
			consultorProyecto.setConsultorProyectoPK(consultorProyectoPK);
			consultorProyecto.setAsignado(1);
			consulProyDAO.save(consultorProyecto);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
}
