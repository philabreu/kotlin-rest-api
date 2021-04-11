package com.financeiro.service

import com.financeiro.model.Entry
import com.financeiro.repository.EntryRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EntryService {

    @Autowired
    private lateinit var repository: EntryRepository

    @Autowired
    private lateinit var personService: PersonService

    fun findAll(): List<Entry> {
        try {
            return repository.findAll()
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun findById(id: Long): Entry {
        try {
            val entry = repository.findByIdOrNull(id)

            return entry ?: throw RuntimeException("registro não encontrado")
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun save(entry: Entry): Entry {
        try {
            validatePerson(entry)

            return repository.save(entry)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun delete(id: Long) {
        try {
            val entry = findById(id)
            repository.delete(entry)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun update(entry: Entry): Entry {
        try {
            val createdEntry = findById(entry.id)

            if (entry.idPerson != createdEntry.idPerson) {
                validatePerson(createdEntry)
            }
            BeanUtils.copyProperties(entry, createdEntry, "id")

            return repository.save(createdEntry)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    private fun validatePerson(entry: Entry) {
        var person = personService.findById(entry.idPerson?.id!!)

        if (person.isInactive()) {
            throw RuntimeException("pessoa ${person.name} esta inativa")
        }
    }
}
