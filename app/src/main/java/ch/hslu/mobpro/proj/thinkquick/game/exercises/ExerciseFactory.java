package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * This class represents a factory that provides methods to create exercises.
 */

public class ExerciseFactory {
    private Random randomGenerator;
    private QuestBacklog questBacklog;

    public ExerciseFactory(final Random randomGenerator, final QuestBacklog questBacklog) {
        this.randomGenerator = randomGenerator;
        this.questBacklog = questBacklog;
    }

    public Exercise easyExercise() {
        final GameSituation gameSituation = generateEasyGameSituation();
        final Quest quest = questBacklog.randomEasyQuest(randomGenerator);
        solveQuest(quest, gameSituation);
        return new RpsExercise(gameSituation, quest);
    }

    private GameSituation generateEasyGameSituation() {
        final Gesture gesture = pickRandomGesture();
        return new GameSituation(gesture, gesture, true);
    }

    public Exercise hardExercise() {
        final GameSituation gameSituation = generateHardGameSituation();
        final Quest hardQuest = questBacklog.randomHardQuest(randomGenerator);
        solveQuest(hardQuest, gameSituation);
        return new RpsExercise(gameSituation, hardQuest);
    }

    private GameSituation generateHardGameSituation() {
        final Gesture winner = pickRandomGesture();
        final Gesture looser = RpsSolver.getLooser(winner);
        return new GameSituation(winner, looser, false);
    }

    private Gesture pickRandomGesture() {
        final Gesture[] gestures = Gesture.values();
        return gestures[randomGenerator.nextInt(gestures.length)];
    }

    private void solveQuest(final Quest quest, final GameSituation situation) {
        Gesture answer = null;
        if (situation.isDraw()) {
            switch (quest.getQuestTarget()) {
                case WIN:
                    answer = RpsSolver.getWinner(situation.getLeftHand());
                    break;
                case LOSE:
                    answer = RpsSolver.getLooser(situation.getLeftHand());
                    break;
                case DRAW:
                    answer = RpsSolver.getDraw(situation.getLeftHand());
            }
        } else {
            switch (quest.getQuestTarget()) {
                case WIN:
                    if (quest.isAgainstWinner()) {
                        answer = RpsSolver.getWinner(situation.getWinner());
                    } else {
                        answer = RpsSolver.getWinner(situation.getLooser());
                    }
                    break;
                case LOSE:
                    if (quest.isAgainstWinner()) {
                        answer = RpsSolver.getLooser(situation.getWinner());
                    } else {
                        answer = RpsSolver.getLooser(situation.getLooser());
                    }
                    break;
                case DRAW:
                    if (quest.isAgainstWinner()) {
                        answer = RpsSolver.getDraw(situation.getWinner());
                    } else {
                        answer = RpsSolver.getDraw(situation.getLooser());
                    }
            }
        }
        quest.setAnswer(answer);
    }
}