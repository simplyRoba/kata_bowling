package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */
class Line(
    private val frames: Frames
) {

    companion object {
        private const val LAST_FRAME_INDEX = 9
        private const val FULL_POINTS = 10
    }

    fun calculateScore(): Int {
        return frames
            .mapIndexed { index, frame -> parseFrameScore(frame, index) }
            .sum()
    }

    private fun parseFrameScore(frame: Frame, currentFrameIndex: Int): Int {
        return when {
            frame.isStrike() -> calculateStrike(currentFrameIndex)
            frame.isSpare() -> calculateSpare(currentFrameIndex)
            else -> with(frame) { firstRoll.points() + secondRoll.points() }
        }
    }

    private fun calculateStrike(currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX -> {
                val currentFrame = frames[currentFrameIndex]
                 with(currentFrame) {
                     FULL_POINTS
                         .plus(firstBonusRoll.points())
                         .plus(secondBonusRoll.points())
                 }
            }
            else -> {
                val nextFrame = frames.nextFrame(currentFrameIndex)
                with(nextFrame) {
                    FULL_POINTS
                        .plus(firstRoll.points())
                        .plus(secondRoll.points())
                }
            }
        }
    }

    private fun calculateSpare(currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX -> FULL_POINTS
                .plus(frames[currentFrameIndex].firstBonusRoll.points())
            else -> FULL_POINTS
                .plus(frames.nextFrame(currentFrameIndex).firstRoll.points())
        }
    }
}
