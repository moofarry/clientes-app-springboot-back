package com.moofarrif.springboot.backend.apirest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;
import com.moofarrif.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAllByOrderByIdAsc();
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> resMap = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			resMap.put("mensaje", "Error al realizar la consulta en la base de datos");
			resMap.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			resMap.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) { // transforma json y lo
																																																// mapea obj cliente
		Cliente clienteNew = null;
		Map<String, Object> resMap = new HashMap<>();

		if (result.hasErrors()) {
			// Recibo un file error lista -> stream-> operador en map a String empleando
			// expresion lambda-> collet

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());
			resMap.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			resMap.put("mensaje", "Error al realizar el INSERT en la base de datos");
			resMap.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		resMap.put("mensaje", "El cliente ha sido creado con éxito!");
		resMap.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {

		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdate = null;

		Map<String, Object> resMap = new HashMap<>();

		if (clienteActual == null) {
			resMap.put("mensaje",
					"Error: No se pudo editar, el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());

			clienteUpdate = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			resMap.put("mensaje", "Error al ractualizar el cliente en la base de datos");
			resMap.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		resMap.put("mensaje", "El cliente ha sido actualizado con éxito!");
		resMap.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.CREATED);

	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> resMap = new HashMap<>();

		try {
			clienteService.delete(id); // springData por crudRepository calida que el cliente exista mediante el id

		} catch (DataAccessException e) {
			resMap.put("mensaje", "Error al eliminar el cliente en la base de datos");
			resMap.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		resMap.put("mensaje", "El cliente eliminado con éxito!");

		return new ResponseEntity<Map<String, Object>>(resMap, HttpStatus.OK);// springData por crudRepository calida que el
																																					// cliente exista mediante el id

	}

	/*
	 * @PostMapping("/clientes")
	 * 
	 * @ResponseStatus(code = HttpStatus.CREATED) public ResponseEntity<Cliente>
	 * saveUser(@RequestBody Cliente cliente){ Cliente createdClient =
	 * clienteService.save(cliente); try { return ResponseEntity.created( new
	 * URI("/api/clientes/"+ createdClient.getId()) ).body(createdClient); } catch
	 * (Exception e){ return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 * } }
	 */

}
