package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nirho.dao.ProyectoPVCAreaDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPO;
import com.nirho.model.ProyectoPVCArea;
import com.nirho.service.ProyectoPVCAreaService;

public class ProyectoPVCAreaServiceImpl implements ProyectoPVCAreaService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOAmpActividadServiceImpl.class);
	
	@Autowired
	private ProyectoPVCAreaDAO proyectoPVCAreaDAO;
	
	@Override
	public ProyectoPVCArea getOne(Integer id) throws NirhoServiceException {
		try {
			return proyectoPVCAreaDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public List<ProyectoPVCArea> getByProyecto(Integer idProyecto) throws NirhoServiceException {
		try {
			return proyectoPVCAreaDAO.findByIdProyecto(idProyecto);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(ProyectoPVCArea entity) throws NirhoServiceException {
		try {
			try {
				ProyectoPVCArea f = proyectoPVCAreaDAO.getOne(entity.getId());
				if(f == null) {
					proyectoPVCAreaDAO.save(entity);
				} else {
					proyectoPVCAreaDAO.update(entity);
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
