package ch.hslu.mobpro.proj.thinkquick.game.checker;

/**
 * Created by Alan Meile on 11.05.2017.
 */

public class PointCalculator {
    private static final int POINT_FACTOR = 10;
    private static final int POINT_EXPONENT = 4;

    private PointCalculator() {}

    public static int calcPoints(int currentProgress, int maxProgress) {
        float percentage = getPercentage(maxProgress, currentProgress);
        return algorithm(percentage);
    }

    private static float getPercentage(int maxProgress, int currentProgress) {
        return (float) currentProgress / maxProgress;
    }

    private static int algorithm(float percentage) {
        float scoredPoints = percentage * POINT_FACTOR;
        return ceiling(exponential(scoredPoints));
    }

    private static int ceiling(float exponential) {
        return (int) Math.ceil(exponential);
    }

    private static float exponential(float scoredPoints) {
        return (float) Math.pow(scoredPoints, POINT_EXPONENT);
    }
}