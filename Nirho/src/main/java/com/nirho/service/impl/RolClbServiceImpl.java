package com.nirho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.RolClbSubmoduloDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.ClbSubmodulo;
import com.nirho.model.RolClbSubmodulo;
import com.nirho.service.RolClbService;

@Service
public class RolClbServiceImpl implements RolClbService {
	public final static Logger logger = Logger.getLogger(RolClbServiceImpl.class);
	
	@Autowired
	private RolClbSubmoduloDAO dao;

	@Override
	public List<ClbSubmodulo> obtenerSubModulos(int rol) throws NirhoServiceException {
		List<ClbSubmodulo> submodulos = new ArrayList<>();
		try {
			for(RolClbSubmodulo rolsub: dao.findAll()) {
				if(rolsub.getPk().getRol() == rol) {
					submodulos.add(rolsub.getPk().getSubmodulo());
				}
			}
		} catch (Exception e) {
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
		return submodulos;
	}
	


}
