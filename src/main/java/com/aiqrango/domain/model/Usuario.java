package com.aiqrango.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String nome;
	
	@Column(nullable = false)
	String email;
	
	@Column(nullable = false)
	String senha;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	LocalDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	List<Grupo> grupos = new ArrayList<>();
	
}
