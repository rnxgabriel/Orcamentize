package com.gabrielaltruist.orcamentize.material_feature.domain.model

import MaterialEntity

data class Material(
    val id: Int,
    val name: String,
    val label: String,
    val description: String,
    val measure: Measure,
    val eMeasure: EMeasure,
    val costPrice: Double,
    val salePrice: Double
) {
    fun toEntity(): MaterialEntity {
        val (v1, v2, v3) = when (val m = this.measure) {
            is Measure.Quantity -> Triple(m.quantity.toDouble(), null, null)
            is Measure.Linear -> Triple(m.length, null, null)
            is Measure.M2 -> Triple(m.width, m.height, null)
            is Measure.M3 -> Triple(m.width, m.height, m.depth)
        }

        return MaterialEntity(
            id = this.id,
            name = this.name,
            label = this.label,
            description = this.description,
            costPrice = this.costPrice,
            salePrice = this.salePrice,
            eMeasure = EMeasure.fromMeasure(this.measure),
            measureValue1 = v1,
            measureValue2 = v2,
            measureValue3 = v3
        )
    }
}

