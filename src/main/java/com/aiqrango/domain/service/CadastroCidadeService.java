package com.aiqrango.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aiqrango.domain.exception.CidadeNaoEncontradaException;
import com.aiqrango.domain.exception.EntidadeEmUsoException;
import com.aiqrango.domain.model.Cidade;
import com.aiqrango.domain.model.Estado;
import com.aiqrango.domain.repository.CidadeRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CadastroCidadeService {

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	CadastroEstadoService cadastroEstadoService;

	public static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}

}
