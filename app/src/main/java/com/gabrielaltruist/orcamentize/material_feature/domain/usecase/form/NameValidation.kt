package com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form

import com.gabrielaltruist.orcamentize.util.validation.ValidationResult

class NameValidation {
    fun execute(name: String): ValidationResult {
        if (name.isEmpty()) {
            return ValidationResult(false, "O nome não pode ser vazio")
        }
        if (name.length < 3) {
            return ValidationResult(false, "O nome deve ter no mínimo 3 caracteres")
        }
        if (name.length > 50) {
            return ValidationResult(false, "O nome deve ter no máximo 50 caracteres")
        }
        return ValidationResult(true)
    }
}