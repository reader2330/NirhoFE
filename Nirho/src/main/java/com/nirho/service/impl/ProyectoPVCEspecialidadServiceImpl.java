package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ProyectoPVCEspecialidadDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.model.ProyectoPVCEspecialidad;
import com.nirho.service.ProyectoPVCEspecialidadService;

@Service
public class ProyectoPVCEspecialidadServiceImpl implements ProyectoPVCEspecialidadService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ProyectoPVCEspecialidadDAO proyectoPVCEspecialidadDAO;
	
	@Override
	public ProyectoPVCEspecialidad getOne(Integer id) throws NirhoServiceException {
		try {
			return proyectoPVCEspecialidadDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(ProyectoPVCEspecialidad entity) throws NirhoServiceException {
		try {
			try {
				ProyectoPVCEspecialidad f = proyectoPVCEspecialidadDAO.getOne(entity.getId());
				if(f == null) {
					proyectoPVCEspecialidadDAO.save(entity);
				} else {
					proyectoPVCEspecialidadDAO.update(entity);
				}
			} catch(Exception e) {
				logger.info("Exception [" + e.getMessage() + "");
			}	
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(List<ProyectoPVCEspecialidad> list) throws NirhoServiceException {
		try {
			for(ProyectoPVCEspecialidad p: list) {
				try {
					ProyectoPVCEspecialidad area = proyectoPVCEspecialidadDAO.getOne(p.getId());
					if(area == null) {
						proyectoPVCEspecialidadDAO.save(p);
					} else {
						proyectoPVCEspecialidadDAO.update(p);
					}
				} catch(Exception e) {
					logger.info("Exception [" + e.getMessage() + "");
				}	
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
}
