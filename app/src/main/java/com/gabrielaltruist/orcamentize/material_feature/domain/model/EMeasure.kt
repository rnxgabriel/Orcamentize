package com.gabrielaltruist.orcamentize.material_feature.domain.model

enum class EMeasure {
    M2, Unidade, M3, Linear;

    val displayName: String
        get() = when (this) {
            M2 -> "M²"
            Unidade -> "Unidade"
            M3 -> "M³"
            Linear -> "Linear"
        }
}