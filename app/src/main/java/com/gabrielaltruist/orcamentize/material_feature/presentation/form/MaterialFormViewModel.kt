package com.gabrielaltruist.orcamentize.material_feature.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Material
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Measure.Linear
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Measure.M2
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Measure.M3
import com.gabrielaltruist.orcamentize.material_feature.domain.model.Measure.Quantity
import com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form.LabelValidation
import com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form.MeasureValidation
import com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form.NameValidation
import com.gabrielaltruist.orcamentize.material_feature.domain.usecase.form.PriceValidation
import com.gabrielaltruist.orcamentize.navigation.AppRoute
import com.gabrielaltruist.orcamentize.navigation.MaterialFormRoute
import com.gabrielaltruist.orcamentize.navigation.NavigateBack
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MaterialFormViewModel(
    private val labelValidation: LabelValidation = LabelValidation(),
    private val measureValidation: MeasureValidation = MeasureValidation(),
    private val nameValidation: NameValidation = NameValidation(),
    private val priceValidation: PriceValidation = PriceValidation(),
) : ViewModel() {

    private val _uiState = MutableStateFlow(MaterialFormState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEvent = Channel<AppRoute>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onAction(action: MaterialFormAction) {
        when (action) {
            is MaterialFormAction.OnCostPriceChanged -> _uiState.update { it.copy(costPrice = action.newCostPrice) }
            is MaterialFormAction.OnDescriptionChanged -> _uiState.update { it.copy(description = action.newDescription) }
            is MaterialFormAction.OnLabelChanged -> _uiState.update { it.copy(label = action.newLabel) }
            is MaterialFormAction.OnMeasureChanged -> _uiState.update {
                it.copy(
                    eMeasure = action.newMeasureType,
                    measureLinear = "",
                    measureQuantidade = "",
                    measureComprimento = "",
                    measureLargura = "",
                    measureProfundidade = ""
                )
            }

            is MaterialFormAction.OnMeasureComprimentoChanged -> _uiState.update {
                it.copy(
                    measureComprimento = action.newComprimento
                )
            }

            is MaterialFormAction.OnMeasureLarguraChanged -> _uiState.update {
                it.copy(
                    measureQuantidade = action.newLargura
                )
            }

            is MaterialFormAction.OnMeasureLinearChanged -> _uiState.update { it.copy(measureLinear = action.newLinear) }
            is MaterialFormAction.OnMeasureProfundidadeChanged -> _uiState.update {
                it.copy(
                    measureProfundidade = action.newProfundidade
                )
            }

            is MaterialFormAction.OnMeasureUnidadeChanged -> _uiState.update {
                it.copy(
                    measureQuantidade = action.newUnidade
                )
            }

            is MaterialFormAction.OnNameChanged -> _uiState.update { it.copy(name = action.newName) }
            is MaterialFormAction.OnSalePriceChanged -> _uiState.update { it.copy(salePrice = action.newSalePrice) }
            MaterialFormAction.Submit -> submitForm()
        }
    }

    fun setFormState(routeData: MaterialFormRoute) {
        if (routeData.materialId == null) return
        TODO("Not yet implemented")
    }

    private fun submitForm() {

        val currentState = _uiState.value

        if (!validateForm(currentState)) return

        val measure = when (currentState.eMeasure) {
            EMeasure.Linear -> Linear(
                currentState.measureLinear.toDouble(),
            )

            EMeasure.M2 -> M2(
                currentState.measureComprimento.toDouble(),
                currentState.measureLargura.toDouble(),
            )

            EMeasure.Quantity -> Quantity(
                currentState.measureQuantidade.toInt(),
            )

            EMeasure.M3 -> M3(
                currentState.measureComprimento.toDouble(),
                currentState.measureLargura.toDouble(),
                currentState.measureProfundidade.toDouble(),
            )
        }

        val material = Material(
            name = currentState.name,
            label = currentState.label,
            description = currentState.description,
            costPrice = currentState.costPrice.toDouble(),
            salePrice = currentState.salePrice.toDouble(),
            measure = measure,
            eMeasure = currentState.eMeasure
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            // TODO: add in Database
            delay(1000) // Simulate network delay
            _uiState.update { it.copy(isSaveSuccess = true) }
            _navigationEvent.send(NavigateBack)
        }
    }


    private fun validateForm(currentState: MaterialFormState): Boolean {

        // Validation
        val validations = listOf(
            nameValidation.execute(currentState.name),
            labelValidation.execute(currentState.label),
            measureValidation.execute(
                currentState.eMeasure,
                currentState.measureLinear.toDoubleOrNull(),
                currentState.measureQuantidade.toIntOrNull(),
                currentState.measureComprimento.toDoubleOrNull(),
                currentState.measureLargura.toDoubleOrNull(),
                currentState.measureProfundidade.toDoubleOrNull()
            ),
            priceValidation.execute(currentState.costPrice, currentState.salePrice)
        )

        if (validations.any { !it.successful }) {
            _uiState.update {
                it.copy(
                    nameError = validations[0].errorMessage,
                    labelError = validations[1].errorMessage,
                    costPriceError = validations[3].errorMessage,
                    salePriceError = validations[3].errorMessage,
                    measureValueError = validations[2].errorMessage
                )
            }
            return false
        }
        return true
    }
}
