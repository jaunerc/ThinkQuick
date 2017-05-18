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

    private GameSituation situationPaperWin, situationPaperWin2, situationScissorWin, situationScissorWin2, situationDraw;

    @Before
    public void setUp() {
        situationPaperWin = new GameSituation(Gesture.ROCK, Gesture.PAPER, false);
        situationPaperWin2 = new GameSituation(Gesture.PAPER, Gesture.ROCK, false);
        situationScissorWin = new GameSituation(Gesture.SCISSOR, Gesture.PAPER, false);
        situationScissorWin2 = new GameSituation(Gesture.PAPER, Gesture.SCISSOR, false);
        situationDraw = new GameSituation(Gesture.ROCK, Gesture.ROCK, true);
    }

    @Test
    public void testGetWinner() {
        assertEquals(Gesture.PAPER, situationPaperWin.getWinner());
        assertEquals(Gesture.PAPER, situationPaperWin2.getWinner());
        assertEquals(Gesture.SCISSOR, situationScissorWin.getWinner());
        assertEquals(Gesture.SCISSOR, situationScissorWin2.getWinner());
    }

    @Test
    public void testGetLooser() {
        assertEquals(Gesture.ROCK, situationPaperWin.getLooser());
        assertEquals(Gesture.PAPER, situationScissorWin.getLooser());
    }

    @Test
    public void testDraw() {
        assertNull(situationDraw.getWinner());
        assertNull(situationDraw.getLooser());
    }
}
