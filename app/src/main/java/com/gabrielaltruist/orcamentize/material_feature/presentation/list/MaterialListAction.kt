package com.gabrielaltruist.orcamentize.features.material.presentation.list

import com.gabrielaltruist.orcamentize.material_feature.domain.model.Material

sealed interface MaterialListAction {
    data class DeleteMaterial(val material: Material) : MaterialListAction
    data object LoadMaterials : MaterialListAction
}