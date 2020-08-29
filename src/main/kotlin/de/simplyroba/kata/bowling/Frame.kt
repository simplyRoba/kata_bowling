package de.simplyroba.kata.bowling

typealias Roll = Char

fun Roll.value(): Int {
    return Character.getNumericValue(this)
}

class Frame(
    val firstRoll: Roll = '0',
    val secondRoll: Roll = '0',
    val firstBonusRoll: Roll = '0'
) {
    companion object {
        private const val SPARE_ROLL = '/'
    }

    fun isSpare() = secondRoll == SPARE_ROLL
}
