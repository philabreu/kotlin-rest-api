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

import com.ingresse.ingresse.model.Category;
import com.ingresse.ingresse.repository.CategoryRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriaServiceTest {

	@Autowired
	private CategoryService categoryService;

	@InjectMocks
	private CategoryService categoriaMock;

	@Mock
	private CategoryRepository categoryRepository;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListarTodos() {
		List<Category> categoriasList = (List<Category>) this.categoryService.findAll();

		Assert.assertNotNull(categoriasList);
		Assert.assertTrue(categoriasList.size() > 0);
		Assert.assertTrue(!categoriasList.isEmpty());
	}

	@Test
	public void testBuscarPorId() {
		Category category = this.categoryService.findOne(1L);

		Assert.assertNotNull(category);
		Assert.assertTrue(category.getId() == 1L);
	}

	@Test
	public void testCriar() {
		Category category = new Category();

		when(categoryRepository.save(any(Category.class))).thenReturn(new Category());
		Assert.assertThat(categoriaMock.save(category), is(notNullValue()));
		Assert.assertThat(categoriaMock.save(category), equalTo(category));
	}
}
