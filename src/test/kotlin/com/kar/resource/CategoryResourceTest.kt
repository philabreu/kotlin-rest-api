package com.kar.resource

import com.kar.model.Category
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Ignore
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

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryResourceTest {
    @Autowired
    private lateinit var trt: TestRestTemplate

    @Test
    fun shouldSaveCategory() {
        val entity = HttpEntity(Category(1, "save category resource"), null)
        val result = trt.postForEntity("/category", entity, Category::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun shouldFindAllCategories() {
        val result = trt.getForEntity("/category", List::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun shouldFindById() {
        val result = trt.getForEntity("/category/{id}", Category::class.java, 18)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun shouldUpdateCategory() {
        val entity = HttpEntity(Category(18, "update teste"), null)
        val result = trt.exchange("/category/{id}", HttpMethod.PUT, entity, Category::class.java, 18)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Ignore("sera refatorado")
    fun deleteTest() {
        val entity = HttpEntity(Category(21, "delete teste"), null)
        val result = trt.exchange("/category/{id}", HttpMethod.DELETE, entity, Void::class.java, 21)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }
}