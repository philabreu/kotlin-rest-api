package com.kar.resource

import com.kar.model.Person
import com.kar.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonResource {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping
    fun findAll(): List<Person> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Person {
        return service.findById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody person: Person): Person {
        return service.save(person)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody person: Person): Person {
        return service.update(person)
    }

}