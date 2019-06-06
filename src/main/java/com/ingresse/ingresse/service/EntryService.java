package com.ingresse.ingresse.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.ingresse.ingresse.model.Lancamento;
import com.ingresse.ingresse.model.Pessoa;
import com.ingresse.ingresse.repository.EntryRepository;
import com.ingresse.ingresse.repository.PersonRepository;

@Service
public class EntryService {

	private static Logger LOGGER = LoggerFactory.getLogger(EntryService.class);

	@Autowired
	private EntryRepository entryRepository;

	@Autowired
	private PersonRepository personRepository;

	public Iterable<Lancamento> findAll() {
		LOGGER.debug("calling findAll method in EntryService:");
		try {
			return entryRepository.findAll();
		} catch (HttpClientErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
		} catch (HttpServerErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
		}
	}

	public Lancamento findById(Long id) {
		LOGGER.debug("calling findById method in EntryService:");
		try {
			Lancamento lancamentoBuscado = entryRepository.findOne(id);
			if (lancamentoBuscado == null) {
				throw new EmptyResultDataAccessException(1);
			}
			
			return lancamentoBuscado;
		} catch (HttpClientErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
		} catch (HttpServerErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
		}
	}

	public Lancamento save(Lancamento lancamento) {
		LOGGER.debug("calling save method in EntryService:");
		try {
			this.validatePerson(lancamento);
			
			return entryRepository.save(lancamento);
		} catch (HttpClientErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
		} catch (HttpServerErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
		}
	}

	public Lancamento update(Lancamento lancamento, Long id) {
		LOGGER.debug("calling update method in EntryService:");
		try {
			Lancamento lancamentoCriado = findById(id);
			
			if (!(lancamento.getPessoa().equals(lancamentoCriado.getPessoa()))) {
				validatePerson(lancamentoCriado);
			}
			BeanUtils.copyProperties(lancamento, lancamentoCriado, "id");
			
			return entryRepository.save(lancamentoCriado);
		} catch (HttpClientErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
		} catch (HttpServerErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
		}
	}

	public void delete(Long id) {
		LOGGER.debug("calling delete method in EntryService:");
		try {
			Lancamento lancamentoBuscado = findById(id);
			entryRepository.delete(lancamentoBuscado);
		} catch (HttpClientErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getCause().getMessage());
		} catch (HttpServerErrorException e) {
			LOGGER.error(ExceptionUtils.getStackTrace(e));
			LOGGER.error(ExceptionUtils.getRootCauseMessage(e));
			throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause().getMessage());
		}
	}

	private void validatePerson(Lancamento lancamento) {
		LOGGER.debug("calling delete validatePerson in EntryService:");
		Pessoa pessoa = null;

		if (lancamento.getPessoa().getId() != null) {
			pessoa = personRepository.findOne(lancamento.getPessoa().getId());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new RuntimeException("error.person.null");
		}
	}
}
