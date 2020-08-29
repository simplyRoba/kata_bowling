package de.simplyroba.kata.bowling

class Line(
        private val frames: ArrayList<Frame>
) {
    fun calculateScore(): Int {
        var score = 0
        for (frame in frames) {
            score += (frame.firstRoll.toInt() + frame.secondRoll.toInt())
        }
        return score
    }
}
