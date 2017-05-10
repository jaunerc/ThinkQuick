package ch.hslu.mobpro.proj.thinkquick.game.tutorial;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alan Meile on 10.05.2017.
 */
public class TutorialFactoryTest {
    private TutorialFactory tutorialFactory;

    @Before
    public void setUp() throws Exception {
        tutorialFactory = new TutorialFactory();
    }

    @Test
    public void getNext() throws Exception {
        tutorialFactory.initModul();
        assertEquals(true, tutorialFactory.hasTutorials());
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        tutorialFactory.getNext();
        assertEquals(false, tutorialFactory.hasTutorials());
    }

    @Test
    public void initModul() throws Exception {
        tutorialFactory.initModul();
        assertEquals(true, tutorialFactory.hasTutorials());
    }

    @Test
    public void hasTutorials() throws Exception {
        assertEquals(false, tutorialFactory.hasTutorials());
    }
}