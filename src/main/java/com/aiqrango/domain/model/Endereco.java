package com.aiqrango.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Embeddable
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Endereco {

	@Column(name = "endereco_cep")
	String cep;
	
	@Column(name = "endereco_logradouro")
	String logradouro;
	
	@Column(name = "endereco_numero")
	String numero;
	
	@Column(name = "endereco_complemento")
	String complemento;
	
	@Column(name = "endereco_bairro")
	String bairro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "endereco_cidade_id")
	Cidade cidade;
	
}
