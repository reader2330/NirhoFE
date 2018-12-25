package com.nirho.service;

import java.util.List;

import com.nirho.exception.NirhoServiceException;
import com.nirho.model.Cliente;

public interface ClienteService {
	
	public void add(Cliente e) throws NirhoServiceException;
	public void update(Cliente e) throws NirhoServiceException;
	public List<Cliente> list() throws NirhoServiceException;
	public Cliente getClienteById(int id) throws NirhoServiceException;
	public void remove(Cliente e) throws NirhoServiceException;
	
}
