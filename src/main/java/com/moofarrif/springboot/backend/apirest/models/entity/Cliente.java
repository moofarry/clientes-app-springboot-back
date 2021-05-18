package com.moofarrif.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	

	@Id
	@GeneratedValue(generator = "SEC_CLIENTES", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SEC_CLIENTES", sequenceName = "SECQ_CLIENTES",allocationSize=1)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String email;
	
	@Column(name="create_at")	
	@Temporal(TemporalType.DATE)
	private Date createAt;	



	private static final long serialVersionUID = 1L;
	
}
