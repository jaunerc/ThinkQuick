package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Test for {@link ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation}
 */

public class GameSituationTest {

    private GameSituation situationPaperWin, situationScissorWin, situationDraw;

    @Before
    public void setUp() {
        situationPaperWin = new GameSituation(Gesture.ROCK, Gesture.PAPER, false);
        situationScissorWin = new GameSituation(Gesture.SCISSOR, Gesture.PAPER, false);
        situationDraw = new GameSituation(Gesture.ROCK, Gesture.ROCK, true);
    }

    @Test
    public void testGetWinner() {
        assertEquals(Gesture.PAPER, situationPaperWin.getWinner());
    }

    @Test
    public void testGetLooser() {
        assertEquals(Gesture.ROCK, situationPaperWin.getLooser());
    }

    @Test
    public void testDraw() {
        assertNull(situationDraw.getWinner());
        assertNull(situationDraw.getLooser());
    }
}
