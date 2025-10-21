package com.gabrielaltruist.orcamentize.util.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)