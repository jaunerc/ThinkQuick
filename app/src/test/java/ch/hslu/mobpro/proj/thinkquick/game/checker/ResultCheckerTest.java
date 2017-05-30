package ch.hslu.mobpro.proj.thinkquick.game.checker;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestTarget;
import ch.hslu.mobpro.proj.thinkquick.game.helper.Gesture;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alan Meile on 11.05.2017.
 */
public class ResultCheckerTest {
    private final static int MAX_PROGRESS = 100;
    private final static Gesture QUEST_ANSWER = Gesture.ROCK;
    private final static Gesture WRONG_ANSWER = Gesture.PAPER;
    private ResultChecker resultChecker;
    private ExerciseResult exerciseResult;
    private Quest quest;

    @Before
    public void setUp() throws Exception {
        resultChecker = new ResultChecker(MAX_PROGRESS);
        quest = new Quest("just a quest", Gesture.PAPER, QuestTarget.WIN, true);
    }

    @Test
    public void checkTrueAnswer() throws Exception {
        exerciseResult = resultChecker.checkAnswer(quest, QUEST_ANSWER, MAX_PROGRESS);
        assertEquals(true, exerciseResult.isCorrect());
    }

    @Test
    public void checkFalseAnswer() throws Exception {
        exerciseResult = resultChecker.checkAnswer(quest, WRONG_ANSWER, MAX_PROGRESS);
        assertEquals(false, exerciseResult.isCorrect());
    }
}