package com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form

import com.gabrielaltruist.orcamentize.util.validation.ValidationResult

class PriceValidation {
    fun execute(costPrice: String, salePrice: String): ValidationResult {
        val cp = costPrice.toDoubleOrNull()
        val sp = salePrice.toDoubleOrNull()

        if (cp == null || cp <= 0) {
            return ValidationResult(
                false,
                "O preço de custo deve ser maior que zero"
            )
        }
        if (sp == null || sp <= 0) {
            return ValidationResult(
                false,
                "O preço de venda deve ser maior que zero"
            )
        }

        if (sp <= cp) {
            return ValidationResult(
                false,
                "O preço de venda deve ser maior que o preço de custo"
            )
        }
        return ValidationResult(true)
    }
}