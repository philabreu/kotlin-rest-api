package com.kar.service

import com.kar.model.Category
import com.kar.model.Entry
import com.kar.model.EntryType
import com.kar.model.Person
import com.kar.repository.EntryRepository
import com.kar.service.EntryService
import junit.framework.Assert.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
class EntryServiceTest {
    @Mock
    private lateinit var service: EntryService

    @Mock
    private lateinit var repository: EntryRepository

    private lateinit var entry: Entry

    @BeforeEach
    fun setup() {
        openMocks(this)
        entry = Entry(
            id = 1, description = "teste", dueDate = LocalDate.now(),
            payDate = LocalDate.now(), value = BigDecimal.valueOf(1),
            observation = "teste", entryType = EntryType.EXPENSE,
            idCategory = Category(1, "teste"),
            idPerson = Person(1, "teste", true, null)
        )
    }

    @Test
    fun shouldFindAllEntries(){
        `when`(repository.findAll()).thenReturn(listOf(entry))
        val result = service.findAll()

        assertNotNull(result)
    }
}