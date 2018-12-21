package com.nirho.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nirho.dao.ProyectoPVCNivelDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCNivel;
import com.nirho.service.ProyectoPVCNivelService;

public class ProyectoPVCNivelServiceImpl implements ProyectoPVCNivelService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ProyectoPVCNivelDAO proyectoPVCNivelDAO;
	
	@Override
	public ProyectoPVCNivel getOne(Integer id) throws NirhoServiceException {
		try {
			return proyectoPVCNivelDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(ProyectoPVCNivel entity) throws NirhoServiceException {
		try {
			try {
				ProyectoPVCNivel f = proyectoPVCNivelDAO.getOne(entity.getId());
				if(f == null) {
					proyectoPVCNivelDAO.save(entity);
				} else {
					proyectoPVCNivelDAO.update(entity);
				}
			} catch(Exception e) {
				logger.info("Exception [" + e.getMessage() + "");
			}	
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
}
