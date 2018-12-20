package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ParticipanteAPOAmpActividadDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPOAmpActividad;
import com.nirho.service.ParticipanteAPOAmpActividadService;

@Service
public class ParticipanteAPOAmpActividadServiceImpl implements ParticipanteAPOAmpActividadService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ParticipanteAPOAmpActividadDAO participanteAPOAmpActividadDAO;
	
	@Override
	public ParticipanteAPOAmpActividad getOne(Integer id) throws NirhoServiceException {
		try {
			return participanteAPOAmpActividadDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
		
	@Override
	public void guardar(List<ParticipanteAPOAmpActividad> actividades) throws NirhoServiceException {
		try {
			for(ParticipanteAPOAmpActividad p: actividades) {
				try {
					ParticipanteAPOAmpActividad funcion = participanteAPOAmpActividadDAO.getOne(p.getId() == null ? 0 : p.getId());
					if(funcion == null) {
						participanteAPOAmpActividadDAO.save(p);
					} else {
						participanteAPOAmpActividadDAO.update(p);
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
	
	@Override
	public void guardar(ParticipanteAPOAmpActividad actividad) throws NirhoServiceException {
		try {
			try {
				ParticipanteAPOAmpActividad f = participanteAPOAmpActividadDAO.getOne(actividad.getId() == null ? 0 : actividad.getId());
				if(f == null) {
					participanteAPOAmpActividadDAO.save(f);
				} else {
					participanteAPOAmpActividadDAO.update(f);
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
