package com.kar.repository

import com.kar.model.Entry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EntryRepository : JpaRepository<Entry, Long> {

}
