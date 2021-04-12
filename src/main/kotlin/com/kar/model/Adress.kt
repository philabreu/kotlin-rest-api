package com.kar.model

import javax.persistence.Embeddable
import javax.validation.constraints.Size

@Embeddable
data class Adress(
    @get:Size(max = 30)
    var district: String,

    @get:Size(max = 30)
    var city: String,

    @get:Size(max = 30)
    var state: String,
)