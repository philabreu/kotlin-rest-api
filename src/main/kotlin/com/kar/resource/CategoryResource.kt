package com.kar.resource

import com.kar.model.Category
import com.kar.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/category")
class CategoryResource {

    @Autowired
    private lateinit var service: CategoryService

    @GetMapping
    fun findAll(): List<Category> {
        return service.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Category {
        return service.findById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody category: Category): Category {
        return service.save(category)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody category: Category): Category {
        return service.update(category)
    }
}