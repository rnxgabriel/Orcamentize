package com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form

import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure
import com.gabrielaltruist.orcamentize.util.validation.ValidationResult

class MeasureValidation {
    fun execute(
        eMeasure: EMeasure,
        measureLinear: Double?,
        measureQuantidade: Int?,
        measureComprimento: Double?,
        measureLargura: Double?,
        measureProfundidade: Double?,
    ): ValidationResult {

        when (eMeasure) {

            EMeasure.Linear -> {
                if (measureLinear == null || measureLinear == 0.0)
                    return ValidationResult(false, "Informe a medida linear")
            }

            EMeasure.Quantity -> {
                if (measureQuantidade == null || measureQuantidade == 0)
                    return ValidationResult(false, "Informe a unidade")

            }

            EMeasure.M2 -> {
                if(measureComprimento == null || measureComprimento == 0.0)
                    return ValidationResult(false, "Informe o comprimento")
                if(measureLargura == null || measureLargura == 0.0)
                    return ValidationResult(false, "Informe a largura")
            }

            EMeasure.M3 -> {
                if(measureComprimento == null || measureComprimento == 0.0)
                    return ValidationResult(false, "Informe o comprimento")
                if(measureLargura == null || measureLargura == 0.0)
                    return ValidationResult(false, "Informe a largura")
                if(measureProfundidade == null || measureProfundidade == 0.0)
                    return ValidationResult(false, "Informe a profundidade")
            }
        }

        return ValidationResult(true)
    }
}