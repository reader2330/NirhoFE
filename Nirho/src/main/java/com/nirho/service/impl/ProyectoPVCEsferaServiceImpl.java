package com.nirho.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ProyectoPVCEsferaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCEsfera;
import com.nirho.service.ProyectoPVCEsferaService;

@Service
public class ProyectoPVCEsferaServiceImpl implements ProyectoPVCEsferaService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ProyectoPVCEsferaDAO proyectoPVCEsferaDAO;
	
	@Override
	public ProyectoPVCEsfera getOne(Integer id) throws NirhoServiceException {
		try {
			return proyectoPVCEsferaDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(ProyectoPVCEsfera entity) throws NirhoServiceException {
		try {
			try {
				ProyectoPVCEsfera f = proyectoPVCEsferaDAO.getOne(entity.getId());
				if(f == null) {
					proyectoPVCEsferaDAO.save(entity);
				} else {
					proyectoPVCEsferaDAO.update(entity);
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
