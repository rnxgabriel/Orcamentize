package com.example.gessolider.material.model


data class Material(
    val name: String,
    val label: String,
    val quantity: Int,
    val labor: Labor,
    val costPrice: Double,
    val salePrice: Double
)
