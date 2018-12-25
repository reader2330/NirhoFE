package com.nirho.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nirho.dao.ClienteDAO;
import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Cliente;
import com.nirho.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	public final static Logger logger = Logger.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteDAO clienteDAO;
	 
	@Override
	public void add(Cliente p) throws NirhoServiceException {
		try {
			clienteDAO.save(p);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public void update(Cliente p) throws NirhoServiceException {
		try {
			clienteDAO.update(p);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al interactuar con la BD, causa [" + e.getMessage()+ "]");
		}
	}
	
	@Override
	public List<Cliente> list() throws NirhoServiceException {
		List<Cliente> clienteList = null;
		try {
			clienteList = clienteDAO.findAll();
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los clientes, causa [" + e.getMessage()+ "]");
		}		
		return clienteList;
	}
	
	@Override
	public Cliente getClienteById(int id) throws NirhoServiceException {
		try {
			return clienteDAO.getOne(id);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los clientes, causa [" + e.getMessage()+ "]");
		}	
	}
	
	@Override
	public void remove(Cliente cliente) throws NirhoServiceException {
		try {
			clienteDAO.delete(cliente);
		} catch(Exception e){
			logger.info("Exception [" + e.getMessage() + "");
			throw new NirhoServiceException("Error al consultar en la BD los clientes, causa [" + e.getMessage()+ "]");
		}	
	}
	

}
