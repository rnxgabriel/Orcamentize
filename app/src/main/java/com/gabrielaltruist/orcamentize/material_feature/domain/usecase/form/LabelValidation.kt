package com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form

import com.gabrielaltruist.orcamentize.util.validation.ValidationResult

class LabelValidation {
    fun execute(label: String): ValidationResult {
        if (label.isEmpty()) {
            return ValidationResult(false, "A etiqueta não pode ser vazia")
        }
        if (label.length > 20) {
            return ValidationResult(false, "A etiqueta não pode ter mais de 20 caracteres")
        }
        return ValidationResult(true)
    }
}