package com.ingresse.ingresse.service;

import static org.hamcrest.CoreMatchers.equalTo;
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

import com.ingresse.ingresse.model.Categoria;
import com.ingresse.ingresse.repository.CategoriaRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService categoriaService;

	@InjectMocks
	private CategoriaService categoriaMock;

	@Mock
	private CategoriaRepository categoriaRepository;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListarTodos() {
		List<Categoria> categoriasList = (List<Categoria>) this.categoriaService.listarTodos();

		Assert.assertNotNull(categoriasList);
		Assert.assertTrue(categoriasList.size() > 0);
		Assert.assertTrue(!categoriasList.isEmpty());
	}

	@Test
	public void testBuscarPorId() {
		Categoria categoria = this.categoriaService.buscarPorId(1L);

		Assert.assertNotNull(categoria);
		Assert.assertTrue(categoria.getId() == 1L);
	}

	@Test
	public void testCriar() {
		Categoria categoria = new Categoria();

		when(categoriaRepository.save(any(Categoria.class))).thenReturn(new Categoria());
		Assert.assertThat(categoriaMock.criar(categoria), is(notNullValue()));
		Assert.assertThat(categoriaMock.criar(categoria), equalTo(categoria));
	}
}
