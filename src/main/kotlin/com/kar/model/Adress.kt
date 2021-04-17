package com.kar.model

import javax.persistence.Embeddable
import javax.validation.constraints.Size

@Embeddable
data class Adress(
    @get:Size(max = 30)
    val district: String,

    @get:Size(max = 30)
    val city: String,

    @get:Size(max = 30)
    val
    state: String,
)