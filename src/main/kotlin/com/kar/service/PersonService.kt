package com.kar.service

import com.kar.model.Person
import com.kar.repository.EntryRepository
import com.kar.repository.PersonRepository
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var entryRepository: EntryRepository

    fun findAll(): List<Person> {
        try {
            return repository.findAll()
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun findById(id: Long): Person {
        try {
            val person = repository.findByIdOrNull(id)

            return person ?: throw RuntimeException("registro nao encontrado")
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun save(person: Person): Person {
        try {
            return repository.save(person)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun delete(id: Long) {
        try {
            val person = findById(id)
            entryRepository.findAll()
                .forEach {
                    if (it.idPerson.id == person.id) {
                        throw RuntimeException("não é possível excluir a pessoa ${person.name} " +
                                "pois pertence a um lançamento")
                    }
                }
            repository.delete(person)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }

    fun update(person: Person): Person {
        try {
            val searchedPerson = findById(person.id)
            BeanUtils.copyProperties(person, searchedPerson, "id")
            return repository.save(searchedPerson)
        } catch (e: DataAccessException) {
            throw RuntimeException(e.cause?.message)
        }
    }
}