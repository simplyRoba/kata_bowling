package de.simplyroba.kata

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * @author simplyroba
 *
 * game = "line"
 * turn = "frame"
 * gutter line = "all zeros games"
 */
internal class BowlingScoreTest {

    companion object {
        private const val GUTTER_LINE_SCORE = 0
    }

    @Test
    internal fun `should calculate gutter line with a zero score`() {
        val frames = arrayListOf<Frame>()
        repeat(10) { frames.add(Frame("0","0")) }
        assertThat(Line(frames).calculateScore()).isEqualTo(GUTTER_LINE_SCORE)
    }

    @Test
    internal fun `should calculate line with one pin down each turn`() {
        val frames = arrayListOf<Frame>()
        repeat(10) { frames.add(Frame("0","0")) }
        assertThat(Line(frames).calculateScore()).isEqualTo(20)
    }
}
