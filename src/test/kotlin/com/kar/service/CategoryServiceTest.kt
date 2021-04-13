package com.kar.service

import com.kar.model.Category
import com.kar.repository.CategoryRepository
import com.kar.service.CategoryService
import junit.framework.Assert.assertNotNull
import org.junit.Ignore
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.data.repository.findByIdOrNull

@RunWith(MockitoJUnitRunner::class)
class CategoryServiceTest {
    @Mock
    private lateinit var service: CategoryService

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    private lateinit var category: Category

    @BeforeEach
    fun setup() {
        openMocks(this)
        category = Category(1, "teste")
    }

    @Test
    fun shouldFindAllCategories() {
        `when`(categoryRepository.findAll()).thenReturn(listOf(category))
        val result = service.findAll()

        assertNotNull(result)
    }

    @Ignore("pesquisar sobre")
    fun shouldFindOneCategory() {
        `when`(categoryRepository.findByIdOrNull(0)).thenReturn(category)
        val resultado = service.findById(0)

        assertNotNull(resultado)
    }
}