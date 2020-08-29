package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */
class Line(
    private val frames: Frames
) {
    fun calculateScore(): Int {
        return frames
            .mapIndexed { index, frame -> parseFrameScore(frame, index) }
            .sum()
    }

    private fun parseFrameScore(currentFrame: Frame, currentFrameIndex: Int): Int {
        return when {
            currentFrame.isStrike() -> calculateStrike(currentFrame, currentFrameIndex)
            currentFrame.isSpare() -> calculateSpare(currentFrame, currentFrameIndex)
            else -> currentFrame.points()
        }
    }

    private fun calculateStrike(currentFrame: Frame, currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX -> currentFrame.points().plus(currentFrame.bonusPoints())
            SECOND_TO_LAST_FRAME_INDEX -> calculateStrikeInSecondToLastFrame(currentFrame)
            else -> calculateStrikeInFirstEightFrames(currentFrame, currentFrameIndex)
        }
    }

    private fun calculateStrikeInFirstEightFrames(currentFrame: Frame,
                                                  currentFrameIndex: Int): Int {
        val nextFrame = frames.nextFrame(currentFrameIndex)
        val afterNextFrame = frames.afterNextFrame(currentFrameIndex)
        return when {
            nextFrame.isStrike() -> {
                currentFrame.points()
                    .plus(nextFrame.firstRoll.points())
                    .plus(afterNextFrame.firstRoll.points())
            }
            else -> currentFrame.points().plus(nextFrame.points())
        }
    }

    private fun calculateStrikeInSecondToLastFrame(currentFrame: Frame): Int {
        val lastFrame = frames.last()
        return when {
            lastFrame.isStrike() -> {
                currentFrame.points()
                    .plus(lastFrame.firstRoll.points())
                    .plus(lastFrame.firstBonusRoll.points())
            }
            else -> currentFrame.points().plus(lastFrame.points())
        }
    }

    private fun calculateSpare(currentFrame: Frame, currentFrameIndex: Int): Int {
        return when (currentFrameIndex) {
            LAST_FRAME_INDEX ->
                currentFrame.points()
                    .plus(currentFrame.firstBonusRoll.points())
            else ->
                currentFrame.points()
                    .plus(frames.nextFrame(currentFrameIndex).firstRoll.points())
        }
    }
}
