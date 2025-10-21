package com.gabrielaltruist.orcamentize.domain.repository

import com.gabrielaltruist.orcamentize.material_feature.domain.model.Material

interface MaterialRepository {
    fun getMaterials(): List<Material>
    fun createMaterial(material: Material)
    fun editMaterial(material: Material)
    fun deleteMaterial(material: Material)
    fun searchMaterial(query: String)
}