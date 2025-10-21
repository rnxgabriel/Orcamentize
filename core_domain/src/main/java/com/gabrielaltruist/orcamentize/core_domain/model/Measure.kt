package com.gabrielaltruist.orcamentize.core_domain.model

sealed class Measure(val value1: Any, val value2: Double?, val value3: Double?) {

    class Unidade(value: Int) : Measure(value, null, null)

    class Linear(value: Double) : Measure(value, null, null)

    class M2(value1: Double, value2: Double) : Measure(value1, value2, null)

    class M3(value1: Double, value2: Double, value3: Double) : Measure(value1, value2, value3)

}