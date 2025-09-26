package com.gabrielaltruist.orcamentize.core.model

sealed class Measure(val value: Any) {
    class Unit(value: Int) : Measure(value)
    class M2(value1: Double, value2: Double) : Measure(Pair(value1, value2))
    class M3(value1: Double, value2: Double, value3: Double) : Measure(Triple(value1, value2, value3))
}