package com.moofarrif.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotEmpty @Size(min = 4,max = 12) 
	@Column(nullable = false)
	private String nombre;	

	@NotEmpty
	@Column(nullable = false)
	private String apellido;	
	
	@NotEmpty @Email
	@Column(unique=true ,nullable = false )
	private String email;
	
	@Column(name="create_at")	
	@Temporal(TemporalType.DATE)
	private Date createAt;	
	
	@PrePersist
	public void rePersist() {
		createAt = new Date();
	}
	
	private static final long serialVersionUID = 1L;
	
	
}
