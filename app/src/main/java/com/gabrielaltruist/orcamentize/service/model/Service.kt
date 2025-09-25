package com.gabrielaltruist.orcamentize.service.model

import com.gabrielaltruist.orcamentize.material.model.Material

data class Service(
    val name: String,
    val materials: List<Material>
)
