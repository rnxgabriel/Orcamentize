package com.gabrielaltruist.orcamentize.material.ui

import com.gabrielaltruist.orcamentize.core.model.Labor
import com.gabrielaltruist.orcamentize.material.domain.model.Material

/**
 * Defines the user actions that can be performed on the Material screens.
 */
sealed interface MaterialAction {
    // Actions for Create/Edit form
    data class OnNameChanged(val name: String) : MaterialAction
    data class OnLabelChanged(val label: String) : MaterialAction
    data class OnCostPriceChanged(val costPrice: String) : MaterialAction
    data class OnSalePriceChanged(val salePrice: String) : MaterialAction
    data class OnMeasureTypeChanged(val measureType: String) : MaterialAction
    data class OnMeasureValue1Changed(val value: String) : MaterialAction
    data class OnMeasureValue2Changed(val value: String) : MaterialAction
    data class OnMeasureValue3Changed(val value: String) : MaterialAction
    data class OnLaborTypeChanged(val laborType: Labor) : MaterialAction
    data object Submit : MaterialAction

    // Actions for Material List
    data class DeleteMaterial(val material: Material) : MaterialAction
    data object LoadMaterials : MaterialAction
}
