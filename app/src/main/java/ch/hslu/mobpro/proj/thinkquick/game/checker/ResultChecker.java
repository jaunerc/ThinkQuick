package ch.hslu.mobpro.proj.thinkquick.game.checker;

import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;

/**
 * Created by Alan Meile on 11.05.2017.
 */

public class ResultChecker {
    private int maxProgress;
    private int minPoints;

    private ResultChecker() {}

    /**
     * Use this class to check and calculate earned points
     * @param maxProgress the maximum value of the Live View (Progressbar)
     */
    public ResultChecker(int maxProgress, int minPoints) {
        this.maxProgress = maxProgress;
        this.minPoints = minPoints;
    }

    public ExerciseResult checkAnswer(Quest quest, Gesture answer, int currentProgress) {
        ExerciseResult exerciseResult = new ExerciseResult();

        if (quest.getAnswer() == answer) {
            answerIsCorrect(exerciseResult, currentProgress);
        } else {
            answerIsWrong(exerciseResult, currentProgress);
        }

        return exerciseResult;
    }

    private void answerIsWrong(ExerciseResult exerciseResult, int currentProgress) {
        exerciseResult.setCorrect(false);
        exerciseResult.setPoints(0);
    }

    private void answerIsCorrect(ExerciseResult exerciseResult, int currentProgress) {
        exerciseResult.setCorrect(true);
        int earnedPoints = PointCalculator.calcPoints(currentProgress, maxProgress, minPoints);
        exerciseResult.setPoints(earnedPoints);
    }
}