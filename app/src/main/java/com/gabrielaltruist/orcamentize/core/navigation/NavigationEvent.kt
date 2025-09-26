package com.gabrielaltruist.orcamentize.core.navigation

sealed class NavigationEvent {

    data object NavigateBack : NavigationEvent()

}