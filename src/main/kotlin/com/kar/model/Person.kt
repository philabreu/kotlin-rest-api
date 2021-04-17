package com.kar.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "person")
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank
    @get:Size(min = 3, max = 50)
    val name: String?,

    @field:NotNull
    val active: Boolean,

    @Embedded
    val adress: Adress?,
) {
    @JsonIgnore
    @Transient
    fun isInactive(): Boolean = !this.active
}