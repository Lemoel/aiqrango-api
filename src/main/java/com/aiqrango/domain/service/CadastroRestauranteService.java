package com.aiqrango.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiqrango.domain.repository.RestauranteRepository;
import com.aiqrango.domain.exception.EntidadeNaoEncontradaException;
import com.aiqrango.domain.model.Cozinha;
import com.aiqrango.domain.model.Restaurante;

@Service
public class CadastroRestauranteService {

	public static final String MSG_RESTAURANTE_NAO_ENCONTRADA = "Não existe cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
				.orElseThrow( () -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA,restauranteId)));
	}
	
}
