package com.moofarrif.springboot.backend.apirest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;
import com.moofarrif.springboot.backend.apirest.models.services.IClienteService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Cliente> saveUser(@RequestBody Cliente cliente){
		Cliente createdClient = clienteService.guardarCliente(cliente);
		try {
			return ResponseEntity.created(
					new URI("/api/clientes/"+ createdClient.getId())
			).body(createdClient);
		} catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	

}
