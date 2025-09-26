package com.gabrielaltruist.orcamentize.material.ui

import com.gabrielaltruist.orcamentize.core.model.Labor
import com.gabrielaltruist.orcamentize.material.domain.model.Material

/**
 * Represents the state of the Create/Edit Material form.
 * It holds the raw string inputs from the UI for easy validation and manipulation.
 */
data class MaterialState(
    val name: String = "",
    val label: String = "",
    val costPrice: String = "",
    val salePrice: String = "",

    val measureType: String = "Unit", // "Unit", "M2", "M3"
    val measureValue1: String = "",
    val measureValue2: String = "",
    val measureValue3: String = "",

    val laborType: Labor = Labor.Point,

    // For validation errors
    val nameError: String? = null,
    val costPriceError: String? = null,
    val salePriceError: String? = null,
    val measureValueError: String? = null,

    val isSaving: Boolean = false,
    val isSaveSuccess: Boolean = false
)

/**
 * Represents the state for the screen that lists all materials.
 */
data class MaterialListState(
    val materials: List<Material> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
