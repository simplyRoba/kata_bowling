package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */
class Frame(
    val firstRoll: Roll = '-',
    val secondRoll: Roll = '-',
    val firstBonusRoll: Roll = '-',
    val secondBonusRoll: Roll = '-'
) {
    companion object {
        private const val SPARE_ROLL = '/'
        private const val STRIKE_ROLL = 'x'
    }

    fun isSpare() = secondRoll == SPARE_ROLL
    fun isStrike() = firstRoll == STRIKE_ROLL

    fun points(): Int {
        return firstRoll.points().plus(secondRoll.points())
    }

    fun bonusPoints(): Int {
        return firstBonusRoll.points().plus(secondBonusRoll.points())
    }
}
