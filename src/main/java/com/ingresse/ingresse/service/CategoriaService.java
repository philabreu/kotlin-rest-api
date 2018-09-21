package com.ingresse.ingresse.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Categoria;
import com.ingresse.ingresse.model.Lancamento;
import com.ingresse.ingresse.repository.CategoriaRepository;
import com.ingresse.ingresse.repository.LancamentoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Iterable<Categoria> listarTodos() {
		return categoriaRepository.findAll();
	}

	public Categoria criar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria buscarPorId(Long id) {
		Categoria categoriaBuscada = categoriaRepository.findOne(id);

		if (categoriaBuscada == null) {
			throw new EmptyResultDataAccessException(1);
		}

		return categoriaBuscada;
	}

	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria categoriaSalva = buscarPorId(id);

		BeanUtils.copyProperties(categoria, categoriaSalva, "id");

		return categoriaRepository.save(categoriaSalva);
	}

	public void remover(Long id) {
		Categoria categoriaBuscada = buscarPorId(id);

		List<Lancamento> listaLancamentos = lancamentoRepository.findAll();
		listaLancamentos.forEach(cadaLancamento -> {
			if (cadaLancamento.getCategoria().getId().equals(categoriaBuscada.getId())) {
				throw new RuntimeException("Categoria não pode ser excluída pois pertence a um lancamento");
			}
		});

		/*lancamentoRepository.findAll().stream().forEach(cadaLancamento -> {
			if (cadaLancamento.getCategoria().getId().equals(categoriaBuscada.getId())) {
				throw new RuntimeException("Categoria não pode ser excluída pois pertence a um lancamento");
			}
		});*/

		categoriaRepository.delete(categoriaBuscada);

	}

}
