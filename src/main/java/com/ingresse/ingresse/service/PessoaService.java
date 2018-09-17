package com.ingresse.ingresse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Pessoa;
import com.ingresse.ingresse.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa buscarPorId(Long id) {
		Pessoa pessoaBuscada = pessoaRepository.findOne(id);

		if (pessoaBuscada == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return pessoaBuscada;
	}

	public Pessoa criar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Pessoa atualizar(Pessoa pessoa, Long id) {
		Pessoa pessoaSalva = buscarPorId(id);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

		return pessoaRepository.save(pessoaSalva);
	}

	public void remover(Long id) {
		Pessoa pessoaBuscada = buscarPorId(id);
		pessoaRepository.delete(pessoaBuscada);
	}

	public Iterable<Pessoa> listarTodos() {
		return pessoaRepository.findAll();
	}
}
