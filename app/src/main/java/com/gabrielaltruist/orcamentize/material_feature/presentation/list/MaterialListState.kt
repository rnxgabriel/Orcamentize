package com.gabrielaltruist.orcamentize.features.material.presentation.list

import com.gabrielaltruist.orcamentize.material_feature.domain.model.Material

data class MaterialListState(
    val materials: List<Material> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
