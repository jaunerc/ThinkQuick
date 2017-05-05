package ch.hslu.mobpro.proj.thinkquick.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alkazua on 05.05.2017.
 */
public class LifeTest {
    private Life life;

    @Before
    public void setUp() throws Exception {
        life = new Life(3, true);
    }

    @Test
    public void removeLife() throws Exception {
        life.removeLife();
        assertEquals(2, life.getLife());
    }

    @Test
    public void getLife() throws Exception {
        assertEquals(3, life.getLife());
    }

    @Test
    public void isAlive() throws Exception {
        assertEquals(true, life.isAlive());
        life.removeLife();
        assertEquals(true, life.isAlive());
        life.removeLife();
        assertEquals(true, life.isAlive());
        life.removeLife();
        assertEquals(false, life.isAlive());
    }
}