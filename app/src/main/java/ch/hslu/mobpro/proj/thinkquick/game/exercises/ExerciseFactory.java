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
        Gesture drawGesture = RpsSolver.getDraw(gameSituation.getLeftHand());

        final Quest easyQuest = questBacklog.randomEasyQuest(randomGenerator, drawGesture);
        return new RpsExercise(gameSituation, easyQuest);
    }

    private GameSituation generateEasyGameSituation() {
        final Gesture gesture = pickRandomGesture();
        return new GameSituation(gesture, gesture, true);
    }

    public Exercise hardExercise() {
        final GameSituation gameSituation = generateHardGameSituation();
        final Quest hardQuest = questBacklog.randomHardQuest(randomGenerator, gameSituation.getLeftHand());
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
}