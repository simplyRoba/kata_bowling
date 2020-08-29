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
    internal fun `should calculate line with one pin each turn`() {
        repeat(10) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 * 2)
    }

    @Test
    internal fun `should calculate line with one spare at the beginning and one pin each other turn`() {
        frames.add(Frame('4','/'))
        repeat(9) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 + 1 + 18)
    }

    @Test
    internal fun `should calculate line with one spare at the end and one pin each other turn`() {
        repeat(9) { frames.add(Frame('1', '1')) }
        frames.add(Frame('6','/', '1'))
        assertThat(Line(frames).calculateScore()).isEqualTo(18 + 10 + 1)
    }

    @Test
    internal fun `should calculate line with one strike at the beginning and one pin each other turn`() {
        frames.add(Frame('x'))
        repeat(9) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 + 1 + 1 + 18)
    }

    @Test
    internal fun `should calculate line with one strike at the end and one pin each other turn`() {
        repeat(9) { frames.add(Frame('1', '1')) }
        frames.add(Frame('x','-', '1', '1'))
        assertThat(Line(frames).calculateScore()).isEqualTo(18 + 10 + 1 + 1)
    }

    @Test
    internal fun `should calculate all strikes`() {
        repeat(9) { frames.add(Frame('x')) }
        frames.add(Frame('x', '-', 'x', 'x'))
        assertThat(Line(frames).calculateScore()).isEqualTo(10 * 30)
    }

    @Test
    internal fun `should calculate 10 pairs of 9 and a miss`() {
        repeat(10) { frames.add(Frame('9')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 * 9)
    }

    @Test
    internal fun `should calculate 10 pairs of 5 and spare with a final 5`() {
        repeat(9) { frames.add(Frame('5', '/')) }
        frames.add(Frame('5', '/', '5'))
        assertThat(Line(frames).calculateScore()).isEqualTo(10 * 15)
    }

    @Test
    internal fun `should calculate strike followed by spare and one pin each other turn`() {
        frames.add(Frame('x'))
        frames.add(Frame('5', '/'))
        repeat(8) { frames.add(Frame('1', '1')) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10 + 5 + 5 + 5 + 5 + 1 + 16)
    }

    @Test
    internal fun `should calculate strike followed misses the rest of the game`() {
        frames.add(Frame('x'))
        repeat(9) { frames.add(Frame()) }
        assertThat(Line(frames).calculateScore()).isEqualTo(10)
    }
}
