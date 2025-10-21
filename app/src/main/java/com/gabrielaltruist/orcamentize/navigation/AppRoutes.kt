package com.gabrielaltruist.orcamentize.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute

data object NavigateBack : AppRoute()

@Serializable
object HomeRoute : AppRoute()

@Serializable
object NewBudgetRoute : AppRoute()

@Serializable
object CreateServiceRoute : AppRoute()

@Serializable
data class MaterialFormRoute(
    val materialId: String? = null
) : AppRoute()

@Serializable
object MaterialListRoute : AppRoute()

@Serializable
object ServiceListRoute : AppRoute()

@Serializable
object BudgetListRoute : AppRoute()
