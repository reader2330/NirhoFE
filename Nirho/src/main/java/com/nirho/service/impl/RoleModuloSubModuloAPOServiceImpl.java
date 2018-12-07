package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.RoleModuloSubModuloAPODAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.RoleModuloSubModuloAPO;
import com.nirho.service.RoleModuloSubModuloAPOService;

@Service
public class RoleModuloSubModuloAPOServiceImpl implements RoleModuloSubModuloAPOService {
	
	public final static Logger logger = Logger.getLogger(ParticipanteAPOServiceImpl.class);
	
	@Autowired
	private RoleModuloSubModuloAPODAO roleModuloSubModuloAPODAO;
	 
	public List<Integer> obtenerSubModulos(int rol) throws NirhoServiceException{
		try {
			List<RoleModuloSubModuloAPO> entities = roleModuloSubModuloAPODAO.obtenerSubModulos(rol);
			List<Integer> result = new ArrayList<>();
			for(RoleModuloSubModuloAPO r: entities) {
				result.add(r.getIdSubmodulo());
			}
			return result;
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
}
