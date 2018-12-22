package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ProyectoPVCConocimientoDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.model.ProyectoPVCConocimiento;
import com.nirho.service.ProyectoPVCConocimientoService;

@Service
public class ProyectoPVCConocimientoServiceImpl implements ProyectoPVCConocimientoService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ProyectoPVCConocimientoDAO proyectoPVCConocimientoDAO;
	
	@Override
	public ProyectoPVCConocimiento getOne(Integer id) throws NirhoServiceException {
		try {
			return proyectoPVCConocimientoDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(ProyectoPVCConocimiento entity) throws NirhoServiceException {
		try {
			try {
				ProyectoPVCConocimiento f = proyectoPVCConocimientoDAO.getOne(entity.getId());
				if(f == null) {
					proyectoPVCConocimientoDAO.save(entity);
				} else {
					proyectoPVCConocimientoDAO.update(entity);
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
	public void guardar(List<ProyectoPVCConocimiento> list) throws NirhoServiceException {
		try {
			for(ProyectoPVCConocimiento p: list) {
				try {
					ProyectoPVCConocimiento area = proyectoPVCConocimientoDAO.getOne(p.getId());
					if(area == null) {
						proyectoPVCConocimientoDAO.save(p);
					} else {
						proyectoPVCConocimientoDAO.update(p);
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
