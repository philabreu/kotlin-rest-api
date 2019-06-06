package com.ingresse.ingresse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Pessoa;
import com.ingresse.ingresse.repository.EntryRepository;
import com.ingresse.ingresse.repository.PersonRepository;

@Service
public class PessoaService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private EntryRepository entryRepository;

	public Pessoa buscarPorId(Long id) {
		Pessoa pessoaBuscada = personRepository.findOne(id);

		if (pessoaBuscada == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return pessoaBuscada;
	}

	public Pessoa criar(Pessoa pessoa) {
		return personRepository.save(pessoa);
	}

	public Pessoa atualizar(Pessoa pessoa, Long id) {
		Pessoa pessoaSalva = buscarPorId(id);

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

		return personRepository.save(pessoaSalva);
	}

	public void remover(Long id) {
		Pessoa pessoaBuscada = buscarPorId(id);
		
		entryRepository.findAll().stream().forEach(lancamento -> {
			if(lancamento.getPessoa().getId().equals(pessoaBuscada.getId())) {
				throw new RuntimeException("pessoa não pode ser excluída pois pertence a um lançamento.");
			}
		});
		
		personRepository.delete(pessoaBuscada);
	}

	public Iterable<Pessoa> listarTodos() {
		return personRepository.findAll();
	}
}
