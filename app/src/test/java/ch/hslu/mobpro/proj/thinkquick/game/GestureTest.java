package ch.hslu.mobpro.proj.thinkquick.game;

import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.helper.Gesture;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alan Meile on 05.05.2017.
 */
public class GestureTest {
    private final static Gesture ROCK = Gesture.ROCK;
    private final static Gesture PAPER = Gesture.PAPER;
    private final static Gesture SCISSOR = Gesture.SCISSOR;

    @Test
    public void testHandSignValues() throws Exception {
        assertEquals(0, ROCK.getValue());
        assertEquals(1, PAPER.getValue());
        assertEquals(2, SCISSOR.getValue());
    }
}