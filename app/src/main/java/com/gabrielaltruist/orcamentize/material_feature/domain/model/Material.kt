package com.gabrielaltruist.orcamentize.material_feature.domain.model

data class Material(
    val name: String,
    val label: String,
    val description: String,
    val measure: Measure,
    val eMeasure: EMeasure,
    val costPrice: Double,
    val salePrice: Double
)