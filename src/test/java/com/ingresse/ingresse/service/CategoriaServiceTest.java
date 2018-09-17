package com.ingresse.ingresse.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ingresse.ingresse.model.Categoria;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService categoriaService;
	
	@Before 
	public void init() throws Exception {
		
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
}
