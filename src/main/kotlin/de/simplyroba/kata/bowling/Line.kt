package de.simplyroba.kata.bowling

class Line(
        private val frames: ArrayList<Frame>
) {
    fun calculateScore(): Int {
        var score = 0
        for ((index, frame) in frames.withIndex()) {
            score += (parseFrameScore(frame, index))
        }
        return score
    }

    private fun parseFrameScore(frame: Frame, frameIndex: Int): Int {
        return when {
            isSpare(frame) -> calculateSpare(frameIndex)
            else -> frame.firstRoll.toInt() + frame.secondRoll.toInt()
        }
    }


    private fun calculateSpare(frameIndex: Int): Int {
        return when {
            frameIndex == 9 -> 10 + frames[frameIndex].firstBonusRoll.toInt()
            else -> 10 + frames[frameIndex + 1].firstRoll.toInt()
        }
    }

    private fun isSpare(frame: Frame): Boolean {
        return frame.secondRoll == "/"
    }

}
