package com.gabrielaltruist.orcamentize.core_domain.model

sealed class Labor {
    object Internal : Labor()
    object External : Labor()
}