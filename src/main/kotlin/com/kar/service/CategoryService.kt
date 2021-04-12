package com.kar.service

import com.kar.model.Category
import com.kar.repository.CategoryRepository
import com.kar.repository.EntryRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var entryRepository: EntryRepository

    fun findAll(): List<Category> {
        try {
            return categoryRepository.findAll()
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun findById(id: Long): Category {
        try {
            val category = categoryRepository.findByIdOrNull(id)

            return category ?: throw RuntimeException("registro nao encontrado")
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun save(category: Category): Category {
        try {
            return categoryRepository.save(category)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun delete(id: Long) {
        try {
            val category = findById(id)
            entryRepository.findAll()
                .forEach {
                    if ((it.idCategory.id) == (category.id)) {
                        throw RuntimeException(
                            "nao eh possivel excluir a categoria ${category.name} " +
                                    "pois pertence a um lançamento"
                        )
                    }
                }
            categoryRepository.delete(category)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun update(category: Category): Category {
        try {
            val searchedCategory = findById(category.id)
            BeanUtils.copyProperties(category, searchedCategory, "id")
            return categoryRepository.save(searchedCategory)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }
}
