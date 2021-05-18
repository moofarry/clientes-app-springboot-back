package com.moofarrif.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.moofarrif.springboot.backend.apirest.models.entity.Cliente;

@Repository
public interface IClientesDao  extends CrudRepository<Cliente, Long>{
		
	

}
