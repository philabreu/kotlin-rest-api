package com.kar.resource

import com.kar.model.Category
import com.kar.model.Entry
import com.kar.model.EntryType
import com.kar.model.Person
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.time.LocalDate


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntryResourceTest {
    @Autowired
    private lateinit var trt: TestRestTemplate

    @Test
    @Order(1)
    fun shouldSaveEntry() {
        val entity = HttpEntity(Entry(id = 1, description = "save entry", dueDate = LocalDate.now(),
            payDate = LocalDate.now(), value = BigDecimal.valueOf(1),
            observation = "teste", entryType = EntryType.EXPENSE,
            idCategory = Category(18, "teste"),
            idPerson = Person(3, "teste", true, null)
        ))
        val result = trt.postForEntity("/entry", entity, Entry::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(2)
    fun shouldFindAllEntry() {
        val result = trt.getForEntity("/entry", List::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(3)
    fun shouldFindById() {
        val result = trt.getForEntity("/entry/{id}", Entry::class.java, 3)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(4)
    fun shouldUpdateEntry() {
        val entity = HttpEntity(Entry(id = 3, description = "update entry", dueDate = LocalDate.now(),
            payDate = LocalDate.now(), value = BigDecimal.valueOf(1),
            observation = "teste", entryType = EntryType.EXPENSE,
            idCategory = Category(18, "teste"),
            idPerson = Person(3, "teste", true, null)
        ))
        val result = trt.exchange("/entry/{id}", HttpMethod.PUT, entity, Entry::class.java, 3)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(5)
    fun shouldDeleteEntry() {
        val entity = HttpEntity(Entry(id = 7, description = "delete entry test", dueDate = LocalDate.now(),
            payDate = LocalDate.now(), value = BigDecimal.valueOf(1),
            observation = "teste", entryType = EntryType.EXPENSE,
            idCategory = Category(1, "teste"),
            idPerson = Person(1, "teste", true, null)
        ))
        val result = trt.exchange("/entry/{id}", HttpMethod.DELETE, entity, Entry::class.java, 7)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }
}