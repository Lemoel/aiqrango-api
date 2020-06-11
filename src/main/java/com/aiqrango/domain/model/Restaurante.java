package com.aiqrango.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	BigDecimal taxaFrete;

	@ManyToOne
	@JoinColumn(name = "cozinha_id", nullable = false)
	Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	List<Produto> produtos = new ArrayList<>();
	
}
