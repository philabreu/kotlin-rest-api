package com.ingresse.ingresse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Categoria;
import com.ingresse.ingresse.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

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

}
