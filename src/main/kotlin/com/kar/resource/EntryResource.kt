package com.kar.resource

import com.kar.model.Entry
import com.kar.service.EntryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/entry")
class EntryResource {

    @Autowired
    private lateinit var service: EntryService

    @GetMapping
    fun findAll(): List<Entry> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Entry {
        return service.findById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody entry: Entry): Entry {
        return service.save(entry)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody entry: Entry): Entry{
        return service.update(entry)
    }
}