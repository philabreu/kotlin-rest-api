package com.kar

import com.kar.model.Category
import com.kar.repository.CategoryRepository
import com.kar.service.CategoryService
import junit.framework.Assert.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired

@RunWith(MockitoJUnitRunner::class)
class CategoryServiceTest {

    @Mock
    lateinit var service: CategoryService

    @Mock
    lateinit var repository: CategoryRepository

    lateinit var category: Category

    @BeforeEach
    fun setup() {
        openMocks(this)
        category = Category(0, "")
    }

    @Test
    fun test() {
        `when`(repository.findAll()).thenReturn(listOf(category))
        val resultado = service.findAll()

        assertNotNull(resultado)
    }
}