package com.ingresse.ingresse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Lancamento;
import com.ingresse.ingresse.model.Pessoa;
import com.ingresse.ingresse.repository.EntryRepository;
import com.ingresse.ingresse.repository.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Iterable<Lancamento> listarTodos() {
		return entryRepository.findAll();
	}

	public Lancamento buscarPorId(Long id) {
		Lancamento lancamentoBuscado = entryRepository.findOne(id);

		if (lancamentoBuscado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return lancamentoBuscado;
	}

	public Lancamento criar(Lancamento lancamento) {
		validarPessoa(lancamento);

		return entryRepository.save(lancamento);
	}

	public Lancamento atualizar(Lancamento lancamento, Long id) {
		Lancamento lancamentoCriado = buscarPorId(id);

		if (!(lancamento.getPessoa().equals(lancamentoCriado.getPessoa()))) {
			validarPessoa(lancamentoCriado);
		}
		BeanUtils.copyProperties(lancamento, lancamentoCriado, "id");

		return entryRepository.save(lancamentoCriado);
	}

	public void remover(Long id) {
		Lancamento lancamentoBuscado = buscarPorId(id);
		entryRepository.delete(lancamentoBuscado);
	}

	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;

		if (lancamento.getPessoa().getId() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new RuntimeException("pessoa inexistente ou inativa.");
		}
	}
}
