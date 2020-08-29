package de.simplyroba.kata.bowling

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * @author simplyroba
 *
 * game = 'line'
 * turn = 'frame'
 * gutter line = 'all zeros games'
 */
internal class ScoreTest {

    private val frames = arrayListOf<Frame>()

    @Test
    internal fun `should calculate gutter line with a zero score`() {
        repeat(10) { frames.add(Frame()) }
        assertThat(Line(frames).calculateScore()).isEqualTo(0)
    }

    @Test
    internal fun `should calculate line with one pin down each turn`() {
        repeat(10) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(20)
    }

    @Test
    internal fun `should calculate line with one spare at the beginning and one pin down each other turn`() {
        frames.add(Frame('4','/'))
        repeat(9) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 + 1 + 18)
    }

    @Test
    internal fun `should calculate line with one spare at the end and one pin down each other turn`() {
        repeat(9) { frames.add(Frame('1', '1')) }
        frames.add(Frame('6','/', '1'))
        assertThat(Line(frames).calculateScore()).isEqualTo(18 + 10 + 1)
    }
}
