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
        val gutterLine = Line()
        assertThat(gutterLine.calculateScore()).isEqualTo(GUTTER_LINE_SCORE)
    }
}
