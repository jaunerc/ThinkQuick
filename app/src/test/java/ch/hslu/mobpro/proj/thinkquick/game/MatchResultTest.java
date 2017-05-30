package ch.hslu.mobpro.proj.thinkquick.game;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.helper.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.helper.MatchResult;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alan Meile on 12.05.2017.
 */
public class MatchResultTest {
    private final static Gesture ROCK = Gesture.ROCK;
    private final static Gesture PAPER = Gesture.PAPER;
    private final static Gesture SCISSOR = Gesture.SCISSOR;
    private MatchResult matchResult;

    @Before
    public void setUp() throws Exception {
        matchResult = new MatchResult();
    }

    @Test
    public void getWinner() throws Exception {
        matchResult.match(ROCK, PAPER);
        assertEquals(PAPER, matchResult.getWinner());
    }

    @Test
    public void isDraw() throws Exception {
        matchResult.match(ROCK, PAPER);
        assertEquals(false, matchResult.isDraw());

        matchResult.match(ROCK, ROCK);
        assertEquals(true, matchResult.isDraw());
    }

    @Test
    public void match() throws Exception {
        matchResult.match(ROCK, ROCK);
        assertEquals(null, matchResult.getWinner());
        assertEquals(true, matchResult.isDraw());
        matchResult.match(ROCK, PAPER);
        assertEquals(PAPER, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
        matchResult.match(ROCK, SCISSOR);
        assertEquals(ROCK, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
    }

    @Test
    public void matchPaper() throws Exception {
        matchResult.match(PAPER, PAPER);
        assertEquals(null, matchResult.getWinner());
        assertEquals(true, matchResult.isDraw());
        matchResult.match(PAPER, ROCK);
        assertEquals(PAPER, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
        matchResult.match(PAPER, SCISSOR);
        assertEquals(SCISSOR, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
    }

    @Test
    public void matchScissor() throws Exception {
        matchResult.match(SCISSOR, SCISSOR);
        assertEquals(null, matchResult.getWinner());
        assertEquals(true, matchResult.isDraw());
        matchResult.match(SCISSOR, ROCK);
        assertEquals(ROCK, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
        matchResult.match(SCISSOR, PAPER);
        assertEquals(SCISSOR, matchResult.getWinner());
        assertEquals(false, matchResult.isDraw());
    }
}