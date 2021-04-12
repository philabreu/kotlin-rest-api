package com.kar.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "entry")
data class Entry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @field:NotBlank
    var description: String,

    @Column(name = "due_date")
    @field:NotNull
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    var dueDate: LocalDate,

    @Column(name = "pay_date")
    @field:NotNull
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    var payDate: LocalDate,

    @field:NotNull
    var value: BigDecimal,

    @get:Size(max = 100)
    @field:NotBlank
    var observation: String,

    @Column(name = "entry_type")
    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    var entryType: EntryType,

    @ManyToOne
    @JoinColumn(name = "id_category")
    @field:NotNull
    var idCategory: Category,

    @ManyToOne
    @JoinColumn(name = "id_person")
    @field:NotNull
    var idPerson: Person,
)