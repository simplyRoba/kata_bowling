package de.simplyroba.kata.bowling

/**
 * @author simplyroba
 */

typealias Frames = ArrayList<Frame>

fun Frames.nextFrame(currentFrameIndex: Int): Frame {
    return this[currentFrameIndex + 1]
}
