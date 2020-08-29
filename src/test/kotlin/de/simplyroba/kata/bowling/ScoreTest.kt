package de.simplyroba.kata.bowling

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * @author simplyroba
 *
 * game = "line"
 * turn = "frame"
 * gutter line = "all zeros games"
 */
internal class ScoreTest {

    companion object {
        private const val GUTTER_LINE_SCORE = 0
    }

    @Test
    internal fun `should calculate gutter line with a zero score`() {
        val frames = arrayListOf<Frame>()
        repeat(10) { frames.add(Frame()) }
        assertThat(Line(frames).calculateScore()).isEqualTo(GUTTER_LINE_SCORE)
    }

    @Test
    internal fun `should calculate line with one pin down each turn`() {
        val frames = arrayListOf<Frame>()
        repeat(10) { frames.add(Frame("1", "1")) }
        assertThat(Line(frames).calculateScore()).isEqualTo(20)
    }

    @Test
    internal fun `should calculate line with one spare and one pin down each other turn`() {
        val frames = arrayListOf<Frame>()
        frames.add(Frame("4","/"))
        repeat(0) { frames.add(Frame("1", "1")) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 + 1 + 18)
    }
}
