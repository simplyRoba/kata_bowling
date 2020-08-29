package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */
class Frame(
    val firstRoll: Roll = '0',
    val secondRoll: Roll = '0',
    val firstBonusRoll: Roll = '0',
    val secondBonusRoll: Roll = '0'
) {
    companion object {
        private const val SPARE_ROLL = '/'
        private const val STRIKE_ROLL = 'x'
    }

    fun isSpare() = secondRoll == SPARE_ROLL
    fun isStrike() = firstRoll == STRIKE_ROLL
}
