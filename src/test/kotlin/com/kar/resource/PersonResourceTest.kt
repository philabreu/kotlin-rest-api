package com.kar.resource

import com.kar.model.Adress
import com.kar.model.Person
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
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonResourceTest {
    @Autowired
    private lateinit var trt: TestRestTemplate

    @Test
    fun shouldSavePerson() {
        val entity = HttpEntity(Person(1, "save person resource",
            true, Adress("foo", "foo", "foo")))
        val result = trt.postForEntity("/person", entity, Person::class.java)

        assertResult(result)
    }

    @Test
    fun shouldFindAllPerson() {
        val result = trt.getForEntity("/person", List::class.java)

        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }

    @Test
    fun shouldFindById() {
        val result = trt.getForEntity("/person/{id}", Person::class.java, 1)

        assertResult(result)
    }

    @Test
    fun shouldUpdatePerson() {
        val entity = HttpEntity(Person(1, "update person resource",
            true, Adress("any", "any", "any")))
        val result = trt.exchange("/person/{id}", HttpMethod.PUT, entity, Person::class.java, 1)

        assertResult(result)
    }

    @Ignore("sera refatorado")
    fun shouldDeletePerson() {
        val entity = HttpEntity(Person(4, "save person resource",
            true, Adress("foo", "foo", "foo")))
        val result = trt.exchange("/person/{id}", HttpMethod.DELETE, entity, Person::class.java, 4)

        assertResult(result)
    }

    private fun assertResult(result: ResponseEntity<Person>) {
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }
}