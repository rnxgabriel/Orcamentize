package com.gabrielaltruist.orcamentize.navigation

sealed class NavigationEvent {
    data object NavigateBack : NavigationEvent()

    data class NavigateTo(val destination: Any) : NavigationEvent()
}