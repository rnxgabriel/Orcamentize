package com.gabrielaltruist.orcamentize.material.domain.model

import com.gabrielaltruist.orcamentize.core.model.Labor
import com.gabrielaltruist.orcamentize.core.model.Measure


data class Material(
    val name: String,
    val label: String,
    val measure: Measure,
    val labor: Labor,
    val costPrice: Double,
    val salePrice: Double
)
