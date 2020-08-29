package de.simplyroba.kata.bowling

typealias Frames = ArrayList<Frame>

fun Frames.nextFrame(currentFrameIndex: Int): Frame {
    return this[currentFrameIndex + 1]
}

class Line(
        private val frames: Frames
) {

    companion object {
        private const val LAST_FRAME_INDEX = 9
        private const val FULL_POINTS = 10
    }

    fun calculateScore(): Int {
        var score = 0
        for ((index, frame) in frames.withIndex()) {
            score += (parseFrameScore(frame, index))
        }
        return score
    }

    private fun parseFrameScore(frame: Frame, currentFrameIndex: Int): Int {
        return when {
            frame.isSpare() -> calculateSpare(currentFrameIndex)
            else -> frame.firstRoll.value() + frame.secondRoll.value()
        }
    }

    private fun calculateSpare(currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX -> FULL_POINTS + frames[currentFrameIndex].firstBonusRoll.value()
            else -> FULL_POINTS + frames.nextFrame(currentFrameIndex).firstRoll.value()
        }
    }
}
