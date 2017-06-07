package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;

import static junit.framework.Assert.assertEquals;

/**
 * Test for {@link ch.hslu.mobpro.proj.thinkquick.game.exercises.RpsSolver}.
 */

public class RpsSolverTest {

    @Test
    public void testGetWinner() {
        assertEquals(Gesture.ROCK, RpsSolver.getWinner(Gesture.SCISSOR));
        assertEquals(Gesture.PAPER, RpsSolver.getWinner(Gesture.ROCK));
        assertEquals(Gesture.SCISSOR, RpsSolver.getWinner(Gesture.PAPER));
    }

    @Test
    public void testGetLooser() {
        assertEquals(Gesture.ROCK, RpsSolver.getLooser(Gesture.PAPER));
        assertEquals(Gesture.PAPER, RpsSolver.getLooser(Gesture.SCISSOR));
        assertEquals(Gesture.SCISSOR, RpsSolver.getLooser(Gesture.ROCK));
    }

    @Test
    public void testGetDraw() {
        assertEquals(Gesture.ROCK, RpsSolver.getDraw(Gesture.ROCK));
        assertEquals(Gesture.PAPER, RpsSolver.getDraw(Gesture.PAPER));
        assertEquals(Gesture.SCISSOR, RpsSolver.getDraw(Gesture.SCISSOR));
    }
}
