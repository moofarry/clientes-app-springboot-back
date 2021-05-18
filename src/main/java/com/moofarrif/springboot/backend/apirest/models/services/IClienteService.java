package com.moofarrif.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;


@Service

public interface IClienteService {
	
	public List<Cliente> findAll();	
	public Cliente guardarCliente(Cliente cliente);

}
