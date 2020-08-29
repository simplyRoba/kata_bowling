package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */

typealias Roll = Char

fun Roll.points(): Int {
    return when {
        isMiss() -> 0
        isStrike() -> 10
        else -> Character.getNumericValue(this)
    }
}

private fun Roll.isMiss(): Boolean {
    return this == '-' || this == '0'
}

private fun Roll.isStrike(): Boolean {
    return this == 'x' || this == 'X'
}
