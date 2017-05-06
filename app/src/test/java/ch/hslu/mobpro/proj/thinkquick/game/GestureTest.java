package ch.hslu.mobpro.proj.thinkquick.game;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alan Meile on 05.05.2017.
 */
public class GestureTest {
    private final static Gesture ROCK = Gesture.ROCK;
    private final static Gesture PAPER = Gesture.PAPER;
    private final static Gesture SCISSOR = Gesture.SCISSOR;
    private final static Gesture DRAW = Gesture.DRAW;

    @Test
    public void testHandSignValues() throws Exception {
        assertEquals(0, ROCK.getValue());
        assertEquals(1, PAPER.getValue());
        assertEquals(2, SCISSOR.getValue());
    }

    @Test
    public void testHandSignMatchUp() throws Exception {
        /* Matchup vs rock */
        assertEquals(ROCK, Gesture.match(ROCK, SCISSOR));
        assertEquals(PAPER, Gesture.match(ROCK, PAPER));
        assertEquals(DRAW, Gesture.match(ROCK, ROCK));

        /* Matchup vs paper */
        assertEquals(PAPER, Gesture.match(PAPER, ROCK));
        assertEquals(SCISSOR, Gesture.match(PAPER, SCISSOR));
        assertEquals(DRAW, Gesture.match(PAPER, PAPER));

        /* Matchup vs scissor */
        assertEquals(SCISSOR, Gesture.match(SCISSOR, PAPER));
        assertEquals(ROCK, Gesture.match(SCISSOR, ROCK));
        assertEquals(DRAW, Gesture.match(SCISSOR, SCISSOR));
    }
}