package com.ingresse.ingresse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ingresse.ingresse.model.Category;
import com.ingresse.ingresse.repository.CategoryRepository;
import com.ingresse.ingresse.repository.EntryRepository;

@Service
public class CategoryService {

	private static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EntryRepository entryRepository;

	public Iterable<Category> findAll() {
		LOGGER.debug("calling findAll method in CategoryService:");
		try {
			return categoryRepository.findAll();
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getCause().getMessage());
		}
	}

	public Category save(Category category) {
		LOGGER.debug("calling save method in CategoryService:");
		try {
			return categoryRepository.save(category);
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getCause().getMessage());
		}
	}

	public Category findOne(Long id) {
		LOGGER.debug("calling findOne method in CategoryService:");
		try {
			Category categoriaBuscada = categoryRepository.findOne(id);
			if (categoriaBuscada == null) {
				throw new EmptyResultDataAccessException("error.entry.notfound", 1);
			}
			return categoriaBuscada;
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getCause().getMessage());
		}

	}

	public Category update(Long id, Category category) {
		LOGGER.debug("calling update method in CategoryService:");
		try {
			Category categoriaSalva = findOne(id);
			BeanUtils.copyProperties(category, categoriaSalva, "id");

			return categoryRepository.save(categoriaSalva);

		} catch (DataAccessException e) {
			throw new RuntimeException(e.getCause().getMessage());
		}

	}

	public void delete(Long id) {
		LOGGER.debug("calling delete method in CategoryService:");
		try {
			Category categoriaBuscada = findOne(id);
			entryRepository.findAll()
			.stream()
			.forEach(cadaLancamento -> {
				if (cadaLancamento.getCategory().getId().equals(categoriaBuscada.getId())) {
					throw new RuntimeException("error.entry.exists");
				}
			});

			categoryRepository.delete(categoriaBuscada);
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getCause().getMessage());
		}
	}

}
