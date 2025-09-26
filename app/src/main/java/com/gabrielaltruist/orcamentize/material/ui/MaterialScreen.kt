package com.gabrielaltruist.orcamentize.material.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gabrielaltruist.orcamentize.core.model.Labor
import com.gabrielaltruist.orcamentize.core.navigation.NavigationEvent
import com.gabrielaltruist.orcamentize.core.ui.theme.AppThemeProvider
import com.gabrielaltruist.orcamentize.material.domain.model.Material
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable

data class MaterialRoute(
    val editMode: Boolean = false,
    @Contextual
    val material: Material? = null
)

@Composable
fun MaterialRoute(navController: NavHostController) {

    val viewModel: MaterialViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                NavigationEvent.NavigateBack -> {
                    navController.popBackStack()
                }

                else -> {}
            }
        }
    }

    MaterialScreen(state = state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialScreen(state: MaterialState, onAction: (MaterialAction) -> Unit) {

    var isMeasureMenuExpanded by remember { mutableStateOf(false) }
    var isLaborMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cadastrar Material") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onAction(MaterialAction.OnNameChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nome do material") },
                singleLine = true,
                isError = state.nameError != null,
                supportingText = { state.nameError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.label,
                onValueChange = { onAction(MaterialAction.OnLabelChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Rótulo") },
                singleLine = true
            )

            ExposedDropdownMenuBox(
                expanded = isMeasureMenuExpanded,
                onExpandedChange = { isMeasureMenuExpanded = !isMeasureMenuExpanded }
            ) {
                OutlinedTextField(
                    value = state.measureType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Medida") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMeasureMenuExpanded) },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isMeasureMenuExpanded,
                    onDismissRequest = { isMeasureMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Unit") },
                        onClick = {
                            onAction(MaterialAction.OnMeasureTypeChanged("Unit"))
                            isMeasureMenuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("M2") },
                        onClick = {
                            onAction(MaterialAction.OnMeasureTypeChanged("M2"))
                            isMeasureMenuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("M3") },
                        onClick = {
                            onAction(MaterialAction.OnMeasureTypeChanged("M3"))
                            isMeasureMenuExpanded = false
                        }
                    )
                }
            }

            when (state.measureType) {
                "Unit" -> {
                    OutlinedTextField(
                        value = state.measureValue1,
                        onValueChange = { onAction(MaterialAction.OnMeasureValue1Changed(it)) },
                        label = { Text("Unidades") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        isError = state.measureValueError != null,
                        supportingText = { state.measureValueError?.let { Text(it) } }
                    )
                }

                "M2" -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = state.measureValue1,
                            onValueChange = { onAction(MaterialAction.OnMeasureValue1Changed(it)) },
                            label = { Text("Comprimento (m)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f),
                            isError = state.measureValueError != null,
                            supportingText = { state.measureValueError?.let { Text(it) } }
                        )
                        OutlinedTextField(
                            value = state.measureValue2,
                            onValueChange = { onAction(MaterialAction.OnMeasureValue2Changed(it)) },
                            label = { Text("Largura (m)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.weight(1f),
                            isError = state.measureValueError != null
                        )
                    }
                }

                "M3" -> {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = state.measureValue1,
                                onValueChange = { onAction(MaterialAction.OnMeasureValue1Changed(it)) },
                                label = { Text("Comprimento (m)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                modifier = Modifier.weight(1f),
                                isError = state.measureValueError != null,
                                supportingText = { state.measureValueError?.let { Text(it) } }
                            )
                            OutlinedTextField(
                                value = state.measureValue2,
                                onValueChange = { onAction(MaterialAction.OnMeasureValue2Changed(it)) },
                                label = { Text("Largura (m)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                modifier = Modifier.weight(1f),
                                isError = state.measureValueError != null
                            )
                        }
                        OutlinedTextField(
                            value = state.measureValue3,
                            onValueChange = { onAction(MaterialAction.OnMeasureValue3Changed(it)) },
                            label = { Text("Altura (m)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            modifier = Modifier.fillMaxWidth(),
                            isError = state.measureValueError != null
                        )
                    }
                }
            }

            ExposedDropdownMenuBox(
                expanded = isLaborMenuExpanded,
                onExpandedChange = { isLaborMenuExpanded = !isLaborMenuExpanded }
            ) {
                OutlinedTextField(
                    value = state.laborType.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Mão de Obra") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isLaborMenuExpanded) },
                    modifier = Modifier.fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isLaborMenuExpanded,
                    onDismissRequest = { isLaborMenuExpanded = false }
                ) {
                    Labor.entries.forEach { labor ->
                        DropdownMenuItem(
                            text = { Text(labor.name) },
                            onClick = {
                                onAction(MaterialAction.OnLaborTypeChanged(labor))
                                isLaborMenuExpanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = state.costPrice,
                onValueChange = { onAction(MaterialAction.OnCostPriceChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Preço de Custo (R$)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.costPriceError != null,
                supportingText = { state.costPriceError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.salePrice,
                onValueChange = { onAction(MaterialAction.OnSalePriceChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Preço de Venda (R$)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.salePriceError != null,
                supportingText = { state.salePriceError?.let { Text(it) } }
            )

            Button(
                onClick = { onAction(MaterialAction.Submit) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isSaving
            ) {
                if (state.isSaving) {
                    CircularProgressIndicator()
                } else {
                    Text("Salvar")
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Composable
fun MaterialScreenPreview() {
    AppThemeProvider(darkTheme = false) {
        MaterialScreen(state = MaterialState(), onAction = {})
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
fun MaterialScreenDarkPreview() {
    AppThemeProvider(darkTheme = true) {
        MaterialScreen(state = MaterialState(nameError = "Erro"), onAction = {})
    }
}
