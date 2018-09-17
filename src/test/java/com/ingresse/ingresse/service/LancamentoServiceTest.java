package com.ingresse.ingresse.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ingresse.ingresse.model.Lancamento;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LancamentoServiceTest {

	@Autowired
	private LancamentoService lancamentoService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void testListarTodos() {
		List<Lancamento> lancamentosList = (List<Lancamento>) this.lancamentoService.listarTodos();

		Assert.assertNotNull(lancamentosList);
		Assert.assertTrue(lancamentosList.size() > 0);
		Assert.assertTrue(!lancamentosList.isEmpty());
	}

	@Test
	public void testBuscarPorId() {
		Lancamento lancamento = this.lancamentoService.buscarPorId(7L);

		Assert.assertNotNull(lancamento);
		Assert.assertTrue(lancamento.getId() == 7L);
	}
}
