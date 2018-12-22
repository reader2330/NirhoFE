package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ParticipantePVCDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipantePVC;
import com.nirho.service.ParticipantePVCService;

@Service
public class ParticipantePVCServiceImpl implements ParticipantePVCService {
	
	public final static Logger logger = Logger.getLogger(ParticipantePVCServiceImpl.class);
	
	@Autowired
	private ParticipantePVCDAO participantePVCDAO;
	
	@Override
	public List<ParticipantePVC> getByProyecto(Integer idProyecto) throws NirhoServiceException{
		try {
			return participantePVCDAO.findByIdProyecto(idProyecto);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void save(List<ParticipantePVC> participantes) throws NirhoServiceException{
		try {
			for(ParticipantePVC p: participantes) {
				try {
					ParticipantePVC participante = participantePVCDAO.getOne(p.getIdParticipante() == null ? 0 : p.getIdParticipante());
					if(participante == null) {
						participantePVCDAO.save(p);
					} else {
						participantePVCDAO.update(p);
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
	public ParticipantePVC getOne(Integer id) throws NirhoServiceException{
		try {
			return participantePVCDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public List<ParticipantePVC> getAll(Integer idProyecto) throws NirhoServiceException{
		List<ParticipantePVC> participantes = new ArrayList<>();
		try {
			participantes = participantePVCDAO.findByIdProyecto(idProyecto);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return participantes;
	}
	
}
