package com.gabrielaltruist.orcamentize.material_feature.domain.model

enum class EMeasure {
    M2, Quantity, M3, Linear;

    val displayName: String
        get() = when (this) {
            M2 -> "M²"
            Quantity -> "Quantidade"
            M3 -> "M³"
            Linear -> "Linear"
        }

    companion object {
        fun fromMeasure(measure: Measure): EMeasure {
            return when (measure) {
                is Measure.M2 -> M2
                is Measure.Quantity -> Quantity
                is Measure.M3 -> M3
                is Measure.Linear -> Linear

            }
        }
    }
}