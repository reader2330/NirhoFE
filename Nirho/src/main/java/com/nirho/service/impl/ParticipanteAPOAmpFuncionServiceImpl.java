package com.nirho.service.impl;

import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ParticipanteAPOAmpFuncionDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ParticipanteAPOAmpFuncion;
import com.nirho.service.ParticipanteAPOAmpFuncionService;

@Service
public class ParticipanteAPOAmpFuncionServiceImpl implements ParticipanteAPOAmpFuncionService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOServiceImpl.class);
	
	@Autowired
	private ParticipanteAPOAmpFuncionDAO participanteAPOAmpFuncionDAO;
	
	@Override
	public ParticipanteAPOAmpFuncion getOne(Integer id) throws NirhoServiceException {
		try {
			return participanteAPOAmpFuncionDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	public List<ParticipanteAPOAmpFuncion> list() throws NirhoServiceException {
		try {
			return participanteAPOAmpFuncionDAO.findAll();
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void guardar(List<ParticipanteAPOAmpFuncion> funciones) throws NirhoServiceException {
		try {
			for(ParticipanteAPOAmpFuncion p: funciones) {
				try {
					ParticipanteAPOAmpFuncion funcion = participanteAPOAmpFuncionDAO.getOne(p.getId() == null ? 0 : p.getId());
					if(funcion == null) {
						participanteAPOAmpFuncionDAO.save(p);
					} else {
						participanteAPOAmpFuncionDAO.update(p);
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
	public void guardar(ParticipanteAPOAmpFuncion funcion) throws NirhoServiceException {
		try {
			try {
				ParticipanteAPOAmpFuncion f = participanteAPOAmpFuncionDAO.getOne(funcion.getId() == null ? 0 : funcion.getId());
				if(f == null) {
					participanteAPOAmpFuncionDAO.save(f);
				} else {
					participanteAPOAmpFuncionDAO.update(f);
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
