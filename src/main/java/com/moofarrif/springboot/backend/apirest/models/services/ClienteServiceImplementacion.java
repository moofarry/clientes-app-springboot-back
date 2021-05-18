package com.moofarrif.springboot.backend.apirest.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.moofarrif.springboot.backend.apirest.dao.IClientesDao;
import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;

@Component
public class ClienteServiceImplementacion implements IClienteService {
	
	@Autowired 
	private IClientesDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
		
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return clienteDao.save(cliente);
	}

}
