package com.gabrielaltruist.orcamentize.material_feature.presentation.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExposedDropdownMenuAnchorType
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.gabrielaltruist.orcamentize.material_feature.domain.model.EMeasure
import com.gabrielaltruist.orcamentize.navigation.AppRoute
import com.gabrielaltruist.orcamentize.navigation.MaterialFormRoute
import com.gabrielaltruist.orcamentize.ui.theme.AppThemeProvider

@Composable
fun MaterialFormRoute(
    routeData: MaterialFormRoute,
    onNavigate: (AppRoute) -> Unit,
) {
    val viewModel: MaterialFormViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) { viewModel.navigationEvent.collect(onNavigate) }

    viewModel.setFormState(routeData)
    MaterialFormScreen(state = state, onAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialFormScreen(state: MaterialFormState, onAction: (MaterialFormAction) -> Unit) {

    var isMeasureMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cadastrar Material") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onAction(MaterialFormAction.OnNameChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nome do material") },
                singleLine = true,
                isError = state.nameError != null,
                supportingText = { state.nameError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.label,
                onValueChange = { onAction(MaterialFormAction.OnLabelChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Etiqueta") },
                singleLine = true,
                isError = state.labelError != null,
                supportingText = { state.labelError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = { onAction(MaterialFormAction.OnDescriptionChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descrição") },
            )

            ExposedDropdownMenuBox(
                expanded = isMeasureMenuExpanded,
                onExpandedChange = { isMeasureMenuExpanded = !isMeasureMenuExpanded }
            ) {
                OutlinedTextField(
                    value = state.eMeasure.displayName,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo De Medida") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMeasureMenuExpanded) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
                )
                ExposedDropdownMenu(
                    expanded = isMeasureMenuExpanded,
                    onDismissRequest = { isMeasureMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Quantidade") },
                        onClick = {
                            onAction(MaterialFormAction.OnMeasureChanged(EMeasure.Quantity))
                            isMeasureMenuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Linear") },
                        onClick = {
                            onAction(MaterialFormAction.OnMeasureChanged(EMeasure.Linear))
                            isMeasureMenuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("M²") },
                        onClick = {
                            onAction(MaterialFormAction.OnMeasureChanged(EMeasure.M2))
                            isMeasureMenuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("M³") },
                        onClick = {
                            onAction(MaterialFormAction.OnMeasureChanged(EMeasure.M3))
                            isMeasureMenuExpanded = false
                        }
                    )
                }
            }

            when (state.eMeasure) {

                EMeasure.Quantity -> OutlinedTextField(
                    value = state.measureQuantidade,
                    onValueChange = { onAction(MaterialFormAction.OnMeasureUnidadeChanged(it)) },
                    label = { Text("Unidades") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.measureValueError != null,
                    supportingText = { state.measureValueError?.let { Text(it) } }
                )

                EMeasure.Linear -> OutlinedTextField(
                    value = state.measureLinear,
                    onValueChange = { onAction(MaterialFormAction.OnMeasureLinearChanged(it)) },
                    label = { Text("Metros Linear") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.measureValueError != null,
                    supportingText = { state.measureValueError?.let { Text(it) } }
                )

                EMeasure.M2 -> {
                    OutlinedTextField(
                        value = state.measureComprimento,
                        onValueChange = { onAction(MaterialFormAction.OnMeasureComprimentoChanged(it)) },
                        label = { Text("Comprimento (m)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        isError = state.measureValueError != null,
                        supportingText = { state.measureValueError?.let { Text(it) } }
                    )
                    OutlinedTextField(
                        value = state.measureLargura,
                        onValueChange = { onAction(MaterialFormAction.OnMeasureLarguraChanged(it)) },
                        label = { Text("Largura (m)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        isError = state.measureValueError != null
                    )
                }

                EMeasure.M3 -> {

                    OutlinedTextField(
                        value = state.measureComprimento,
                        onValueChange = {
                            onAction(
                                MaterialFormAction.OnMeasureComprimentoChanged(
                                    it
                                )
                            )
                        },
                        label = { Text("Comprimento (m)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        isError = state.measureValueError != null,
                        supportingText = { state.measureValueError?.let { Text(it) } }
                    )
                    OutlinedTextField(
                        value = state.measureLargura,
                        onValueChange = { onAction(MaterialFormAction.OnMeasureLarguraChanged(it)) },
                        label = { Text("Largura (m)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.weight(1f),
                        isError = state.measureValueError != null
                    )

                    OutlinedTextField(
                        value = state.measureProfundidade,
                        onValueChange = {
                            onAction(
                                MaterialFormAction.OnMeasureProfundidadeChanged(
                                    it
                                )
                            )
                        },
                        label = { Text("Profundidade (m)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        modifier = Modifier.fillMaxWidth(),
                        isError = state.measureValueError != null
                    )
                }
            }


            OutlinedTextField(
                value = state.costPrice,
                onValueChange = { onAction(MaterialFormAction.OnCostPriceChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Preço de Custo (R$)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.costPriceError != null,
                supportingText = { state.costPriceError?.let { Text(it) } }
            )

            OutlinedTextField(
                value = state.salePrice,
                onValueChange = { onAction(MaterialFormAction.OnSalePriceChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Preço de Venda (R$)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.salePriceError != null,
                supportingText = { state.salePriceError?.let { Text(it) } }
            )

            Button(
                onClick = { onAction(MaterialFormAction.Submit) },
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


@Preview
@Composable
fun MaterialScreenPreview() {
    AppThemeProvider {
        MaterialFormScreen(state = MaterialFormState(), onAction = {})
    }
}
