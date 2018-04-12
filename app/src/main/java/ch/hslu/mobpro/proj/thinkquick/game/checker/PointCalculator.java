package ch.hslu.mobpro.proj.thinkquick.game.checker;

/**
 * Created by Alan Meile on 11.05.2017.
 */

public class PointCalculator {
    private static final int POINT_HELP = 20;
    private static final int MIN_POINTS = 100;
    private static final int POINT_FACTOR = 10;
    private static final int POINT_EXPONENT = 3;

    private PointCalculator() {}

    public static int calcPoints(int currentProgress, int maxProgress, int minPoints) {
        float percentage = getPercentage(maxProgress, currentProgress);
        int points = algorithm(percentage);
        if (points < minPoints) {
            points = minPoints;
        }
        return points;
    }

    private static float getPercentage(int maxProgress, int currentProgress) {
        if (maxProgress - currentProgress <= POINT_HELP) {
            currentProgress = maxProgress;
        } else {
            currentProgress += POINT_HELP;
        }
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