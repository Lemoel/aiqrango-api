package com.aiqrango.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.aiqrango.domain.exception.EntidadeEmUsoException;
import com.aiqrango.domain.exception.EstadoNaoEncontradoException;
import com.aiqrango.domain.model.Estado;
import com.aiqrango.domain.repository.EstadoRepository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CadastroEstadoService {

	public static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";

	@Autowired
	EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}

}
