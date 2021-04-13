package com.kar

import com.kar.model.Category
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

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryResourceTest {
    @Autowired
    lateinit var trt: TestRestTemplate

    @Test
    @Order(1)
    fun saveTest() {
        val entity = HttpEntity(Category(1, "save category resource"), null)
        val result = trt.postForEntity("/category", entity, Category::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(2)
    fun findAllTest() {
        val result = trt.getForEntity("/category", List::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(3)
    fun findByIdTest() {
        val result = trt.getForEntity("/category/{id}", Category::class.java, 19)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(4)
    fun updateTest() {
        val entity = HttpEntity(Category(18, "update teste"), null)
        val result = trt.exchange("/category/{id}", HttpMethod.PUT, entity, Category::class.java, 18)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    @Order(5)
    fun deleteTest() {
        val entity = HttpEntity(Category(19, "delete teste"), null)
        val result = trt.exchange("/category/{id}", HttpMethod.DELETE, entity, Void::class.java, 19)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }
}