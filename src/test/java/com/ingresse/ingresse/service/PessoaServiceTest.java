package com.ingresse.ingresse.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ingresse.ingresse.model.Pessoa;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PessoaServiceTest {

	@Autowired
	private PessoaService pessoaService;

	@Before
	public void init() throws Exception {

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
}
