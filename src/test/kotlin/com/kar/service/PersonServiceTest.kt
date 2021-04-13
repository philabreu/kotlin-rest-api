package com.kar.service

import com.kar.model.Person
import com.kar.repository.PersonRepository
import com.kar.service.PersonService
import junit.framework.Assert.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonServiceTest {
    @Mock
    private lateinit var service: PersonService

    @Mock
    private lateinit var repository: PersonRepository

    private lateinit var person: Person

    @BeforeEach
    fun setup() {
        openMocks(this)
        person = Person(id = 0, name = "teste", active = true, adress = null)
    }

    @Test
    fun shouldFindAllCategories() {
        Mockito.`when`(repository.findAll()).thenReturn(listOf(person))
        val result = service.findAll()

        assertNotNull(result)
    }
}