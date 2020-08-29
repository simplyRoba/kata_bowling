package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */

typealias Roll = Char

fun Roll.points(): Int {
    return Character.getNumericValue(this)
}
