package com.ingresse.ingresse.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ingresse.ingresse.model.Pessoa;
import com.ingresse.ingresse.repository.PessoaRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PessoaServiceTest {

	@Autowired
	private PessoaService pessoaService;

	@InjectMocks
	private PessoaService pessoaMock;

	@Mock
	private PessoaRepository pessoaRepository;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListarTodos() {
		List<Pessoa> pessoasList = (List<Pessoa>) this.pessoaService.listarTodos();

		Assert.assertNotNull(pessoasList);
		Assert.assertTrue(pessoasList.size() > 0);
		Assert.assertTrue(!pessoasList.isEmpty());
	}

	@Test
	public void testBuscarPorId() {
		Pessoa pessoa = this.pessoaService.buscarPorId(4L);

		Assert.assertNotNull(pessoa);
		Assert.assertTrue(pessoa.getId() == 4L);
	}

	@Test
	public void testCriar() {
		Pessoa pessoa = new Pessoa();

		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(new Pessoa());
		Assert.assertThat(pessoaMock.criar(pessoa), is(notNullValue()));
	}
}
