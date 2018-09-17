package com.ingresse.ingresse.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ingresse.ingresse.event.RecursoCriadoEvent;
import com.ingresse.ingresse.model.Lancamento;
import com.ingresse.ingresse.service.LancamentoService;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public Iterable<Lancamento> listarTodos() {
		return lancamentoService.listarTodos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
		Lancamento lancamentoBuscado = lancamentoService.buscarPorId(id);

		return ResponseEntity.ok().body(lancamentoBuscado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		Lancamento lancamentoCriado = lancamentoService.criar(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoCriado.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCriado);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		lancamentoService.remover(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Lancamento> atualizar(@Valid @RequestBody Lancamento lancamento, @PathVariable Long id) {
		try {
			Lancamento lancamentoAtualizado = lancamentoService.atualizar(lancamento, id);

			return ResponseEntity.ok(lancamentoAtualizado);
		} catch (IllegalArgumentException exception) {
			return ResponseEntity.notFound().build();
		}
	}
}
