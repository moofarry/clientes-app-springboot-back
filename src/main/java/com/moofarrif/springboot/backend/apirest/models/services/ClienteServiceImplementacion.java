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
	public List<Cliente> findAllByOrderByIdAsc() {
		return (List<Cliente>) clienteDao.findAllByOrderByIdAsc();
		
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {

		clienteDao.deleteById(id);		
	}

}
