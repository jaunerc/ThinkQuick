package ch.hslu.mobpro.proj.thinkquick.game.checker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;

/**
 * Created by Alan Meile on 11.05.2017.
 */
public class PointCalculatorTest {

    @Test
    public void calcPoints() throws Exception {
        int progress_100 = PointCalculator.calcPoints(100, 100);
        int progress_99 = PointCalculator.calcPoints(99, 100);
        assertEquals(progress_100, progress_99);

        int progress_50 = PointCalculator.calcPoints(50, 100);
        int progress_49 = PointCalculator.calcPoints(49, 100);
        assertThat(progress_50, greaterThan(progress_49));

        int progress_1 = PointCalculator.calcPoints(1, 100);
        int progress_0 = PointCalculator.calcPoints(0, 100);
        assertEquals(progress_1, progress_0);
    }
}