package com.gabrielaltruist.orcamentize.material_feature.presentation.form

import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure

sealed interface MaterialFormAction {
    data class OnNameChanged(val newName: String) : MaterialFormAction
    data class OnLabelChanged(val newLabel: String) : MaterialFormAction
    data class OnDescriptionChanged(val newDescription: String) : MaterialFormAction
    data class OnCostPriceChanged(val newCostPrice: String) : MaterialFormAction
    data class OnSalePriceChanged(val newSalePrice: String) : MaterialFormAction
    data class OnMeasureChanged(val newMeasureType: EMeasure) : MaterialFormAction
    data class OnMeasureLinearChanged(val newLinear: String) : MaterialFormAction
    data class OnMeasureUnidadeChanged(val newUnidade: String) : MaterialFormAction
    data class OnMeasureComprimentoChanged(val newComprimento: String) : MaterialFormAction
    data class OnMeasureLarguraChanged(val newLargura: String) : MaterialFormAction
    data class OnMeasureProfundidadeChanged(val newProfundidade: String) : MaterialFormAction
    data object Submit : MaterialFormAction
}