package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */
class Line(
    private val frames: Frames
) {
    companion object {
        private const val LAST_FRAME_INDEX = 9
        private const val SECOND_TO_LAST_FRAME_INDEX = 8
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
            else -> frame.points()
        }
    }

    private fun calculateStrike(currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX -> calculateStrikeInLastFrame()
            SECOND_TO_LAST_FRAME_INDEX -> calculateStrikeInSecondToLastFrame()
            else -> calculateStrikeInFirstEightFrames(currentFrameIndex)
        }
    }

    private fun calculateStrikeInFirstEightFrames(currentFrameIndex: Int): Int {
        val nextFrame = frames.nextFrame(currentFrameIndex)
        val afterNextFrame = frames.nextFrame(currentFrameIndex + 1)
        return when {
            nextFrame.isStrike() -> {
                FULL_POINTS
                    .plus(nextFrame.firstRoll.points())
                    .plus(afterNextFrame.firstRoll.points())
            }
            else -> FULL_POINTS.plus(nextFrame.points())
        }
    }

    private fun calculateStrikeInSecondToLastFrame(): Int {
        val lastFrame = frames[LAST_FRAME_INDEX]
        with(lastFrame) {
            return when {
                isStrike() -> {
                    FULL_POINTS
                        .plus(firstRoll.points())
                        .plus(firstBonusRoll.points())
                }
                else -> FULL_POINTS.plus(points())
            }
        }
    }

    private fun calculateStrikeInLastFrame(): Int {
        return FULL_POINTS.plus(frames.last().bonusPoints())
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
