package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */

typealias Frames = ArrayList<Frame>

const val LAST_FRAME_INDEX = 9
const val SECOND_TO_LAST_FRAME_INDEX = 8

fun Frames.nextFrame(currentFrameIndex: Int): Frame {
    return this[currentFrameIndex + 1]
}

fun Frames.afterNextFrame(currentFrameIndex: Int): Frame {
    return this[currentFrameIndex + 2]
}
