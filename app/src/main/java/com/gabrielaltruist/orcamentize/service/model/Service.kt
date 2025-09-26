package com.gabrielaltruist.orcamentize.service.model

import com.gabrielaltruist.orcamentize.material.domain.model.Material

data class Service(
    val name: String,
    val materials: List<Material>
)
