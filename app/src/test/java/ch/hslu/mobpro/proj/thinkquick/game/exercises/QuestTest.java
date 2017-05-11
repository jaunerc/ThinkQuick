package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

import static org.junit.Assert.*;

/**
 * Created by Alan Meile on 11.05.2017.
 */
public class QuestTest {
    private final static String TEST_QUEST_INFO = "Win against the winner";
    private final static Gesture TEST_ANSWER = Gesture.ROCK;
    private Quest quest;

    @Before
    public void setUp() throws Exception {
        quest = new Quest(TEST_QUEST_INFO, TEST_ANSWER);
    }

    @Test
    public void getInfo() throws Exception {
        assertEquals(TEST_QUEST_INFO, quest.getInfo());
    }

    @Test
    public void getAnswer() throws Exception {
        assertEquals(TEST_ANSWER, quest.getAnswer());
    }
}