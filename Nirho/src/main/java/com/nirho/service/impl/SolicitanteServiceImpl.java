package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.SolicitanteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Solicitante;
import com.nirho.service.SolicitanteService;

@Service
public class SolicitanteServiceImpl implements SolicitanteService {
	
	public final static Logger logger = Logger.getLogger(SolicitanteServiceImpl.class);
	
	@Autowired
	private SolicitanteDAO solicitanteDAO;
	
	@Override
	public List<Solicitante> getByRFC(String rfc) throws NirhoServiceException{
		try {
			return solicitanteDAO.findByRfc(rfc);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	 
	@Override
	public Solicitante getByUsername(String username) throws NirhoServiceException{
		try {
			return solicitanteDAO.findByUsername(username).get(0);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void save(Solicitante solicitante) throws NirhoServiceException{
		try {
			Solicitante s = solicitanteDAO.getOne(solicitante.getId());
			if(s == null) {
				solicitanteDAO.save(solicitante);
			} else {
				solicitanteDAO.update(solicitante);
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void save(List<Solicitante> solicitantes) throws NirhoServiceException{
		try {
			for(Solicitante p: solicitantes) {
				try {
					Solicitante s = solicitanteDAO.getOne(p.getId());
					if(s == null) {
						solicitanteDAO.save(p);
					} else {
						solicitanteDAO.update(p);
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
	public Solicitante getOne(long id) throws NirhoServiceException{
		try {
			return solicitanteDAO.getOne(id);
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public List<Solicitante> getAll() throws NirhoServiceException{
		List<Solicitante> participantes = new ArrayList<>();
		try {
			participantes = solicitanteDAO.findAll();
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return participantes;
	}
	
}
