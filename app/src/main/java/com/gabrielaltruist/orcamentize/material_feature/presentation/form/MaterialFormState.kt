package com.gabrielaltruist.orcamentize.material_feature.presentation.form

import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure

data class MaterialFormState(
    val name: String = "",
    val label: String = "",
    val description: String = "",
    val costPrice: String = "",
    val salePrice: String = "",

    val measureLinear: String = "",
    val measureUnidade: String = "",
    val measureComprimento: String = "",
    val measureLargura: String = "",
    val measureProfundidade: String = "",

    val eMeasure: EMeasure = EMeasure.Unidade,

    // For validation errors
    val nameError: String? = null,
    val labelError: String? = null,
    val costPriceError: String? = null,
    val salePriceError: String? = null,
    val measureValueError: String? = null,

    val isSaving: Boolean = false,
    val isSaveSuccess: Boolean = false
)
