package com.gabrielaltruist.orcamentize.material.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielaltruist.orcamentize.core.model.Measure
import com.gabrielaltruist.orcamentize.core.navigation.NavigationEvent
import com.gabrielaltruist.orcamentize.material.domain.model.Material
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MaterialViewModel : ViewModel() {

    private val _state = MutableStateFlow(MaterialState())
    val state = _state.asStateFlow()

    private val _listState = MutableStateFlow(MaterialListState())
    val listState = _listState.asStateFlow()

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun onAction(action: MaterialAction) {
        when (action) {
            // Form Actions
            is MaterialAction.OnNameChanged -> _state.update { it.copy(name = action.name) }
            is MaterialAction.OnLabelChanged -> _state.update { it.copy(label = action.label) }
            is MaterialAction.OnCostPriceChanged -> _state.update { it.copy(costPrice = action.costPrice) }
            is MaterialAction.OnSalePriceChanged -> _state.update { it.copy(salePrice = action.salePrice) }
            is MaterialAction.OnMeasureTypeChanged -> _state.update { it.copy(measureType = action.measureType) }
            is MaterialAction.OnMeasureValue1Changed -> _state.update { it.copy(measureValue1 = action.value) }
            is MaterialAction.OnMeasureValue2Changed -> _state.update { it.copy(measureValue2 = action.value) }
            is MaterialAction.OnMeasureValue3Changed -> _state.update { it.copy(measureValue3 = action.value) }
            is MaterialAction.OnLaborTypeChanged -> _state.update { it.copy(laborType = action.laborType) }
            MaterialAction.Submit -> submitForm()

            // List Actions
            is MaterialAction.DeleteMaterial -> deleteMaterial(action.material)
            MaterialAction.LoadMaterials -> loadMaterials()
        }
    }

    private fun submitForm() {
        val currentState = _state.value
        val nameResult = currentState.name.isNotBlank()
        val costPriceResult = currentState.costPrice.toDoubleOrNull()
        val salePriceResult = currentState.salePrice.toDoubleOrNull()

        // Basic validation
        _state.update {
            it.copy(
                nameError = if (!nameResult) "Nome não pode estar vazio" else null,
                costPriceError = if (costPriceResult == null) "Preço de custo inválido" else null,
                salePriceError = if (salePriceResult == null) "Preço de venda inválido" else null
            )
        }

        if (!nameResult || costPriceResult == null || salePriceResult == null) {
            return
        }

        val measure = when (currentState.measureType) {
            "Unit" -> {
                val unitValue = currentState.measureValue1.toIntOrNull()
                if (unitValue == null) {
                    _state.update { it.copy(measureValueError = "Valor unitário inválido") }
                    return
                }
                Measure.Unit(unitValue)
            }
            "M2" -> {
                val v1 = currentState.measureValue1.toDoubleOrNull()
                val v2 = currentState.measureValue2.toDoubleOrNull()
                if (v1 == null || v2 == null) {
                    _state.update { it.copy(measureValueError = "Valores de m² inválidos") }
                    return
                }
                Measure.M2(v1, v2)
            }
            "M3" -> {
                val v1 = currentState.measureValue1.toDoubleOrNull()
                val v2 = currentState.measureValue2.toDoubleOrNull()
                val v3 = currentState.measureValue3.toDoubleOrNull()
                if (v1 == null || v2 == null || v3 == null) {
                    _state.update { it.copy(measureValueError = "Valores de m³ inválidos") }
                    return
                }
                Measure.M3(v1, v2, v3)
            }
            else -> {
                // Should not happen
                return
            }
        }
        
        _state.update { it.copy(measureValueError = null) }


        val material = Material(
            name = currentState.name,
            label = currentState.label,
            costPrice = costPriceResult,
            salePrice = salePriceResult,
            measure = measure,
            labor = currentState.laborType
        )

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            Log.println(Log.DEBUG, "MaterialViewModel", "Saving material: $material")
            delay(1000) // Simulate network delay
            _state.update { it.copy(isSaving = false, isSaveSuccess = true) }
        }
    }

    private fun loadMaterials() {
        viewModelScope.launch {
            _listState.update { it.copy(isLoading = true) }
            // TODO: Replace with actual data loading
            delay(1000)
            _listState.update { it.copy(isLoading = false, materials = emptyList()) }
        }
    }

    private fun deleteMaterial(material: Material) {
        viewModelScope.launch {
            // TODO: Implement delete logic
        }
    }
}
