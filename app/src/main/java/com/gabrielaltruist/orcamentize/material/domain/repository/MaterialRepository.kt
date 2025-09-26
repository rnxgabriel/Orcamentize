package com.gabrielaltruist.orcamentize.material.domain.repository

import com.gabrielaltruist.orcamentize.material.domain.model.Material

interface MaterialRepository {
    fun getMaterials(): List<Material>
    fun createMaterial(material: Material): Unit
    fun editMaterial(material: Material): Unit
    fun deleteMaterial(material: Material): Unit
    fun searchMaterial(query: String)
}