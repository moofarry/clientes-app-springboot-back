package com.moofarrif.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;


@Service
public interface IClienteService {
	
	public Cliente findById(Long id);
	
	public List<Cliente> findAllByOrderByIdAsc();


	public Cliente save(Cliente cliente);
	
	public void delete(Long id);

}
