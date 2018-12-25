package com.nirho.dao.impl;

import org.springframework.stereotype.Repository;

import com.nirho.dao.ClienteDAO;
import com.nirho.model.Cliente;

@Repository
public class ClienteDAOImpl extends AbstractDAO<Cliente, Integer> implements ClienteDAO {
	
}
