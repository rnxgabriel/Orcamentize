package com.gabrielaltruist.orcamentize.material_feature.domain.model/**
 * Representa um conjunto fechado de unidades de medida.
 * Cada subclasse armazena apenas os valores necessários para seu cálculo.
 */
sealed class Measure {

    /**
     * Representa uma quantidade contável, como "5 tijolos".
     * @property quantity O número de unidades.
     */
    data class Unidade(val quantity: Int) : Measure()

    /**
     * Representa uma medida linear (comprimento), como "10 metros de fio".
     * @property length O comprimento em metros.
     */
    data class Linear(val length: Double) : Measure()

    /**
     * Representa uma área (metro quadrado), como "20 m² de piso".
     * @property width A largura.
     * @property height A altura ou comprimento.
     */
    data class M2(val width: Double, val height: Double) : Measure()

    /**
     * Representa um volume (metro cúbico), como "2 m³ de concreto".
     * @property width A largura.
     * @property height A altura.
     * @property depth A profundidade.
     */
    data class M3(val width: Double, val height: Double, val depth: Double) : Measure()
}
