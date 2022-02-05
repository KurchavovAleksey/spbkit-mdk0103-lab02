package com.spbkit.kurchavov204.lab02converter

data class Unit(val name: String, val coeff: Float) {
    override fun toString(): String {
        return name
    }

    fun absoluteToUnit(absolute: Float): Float {
        return absolute * coeff
    }

    fun unitToAbsolute(units: Float): Float {
        return units / coeff
    }
}

