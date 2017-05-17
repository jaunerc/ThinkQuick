package ch.hslu.mobpro.proj.thinkquick.game;

import android.app.Activity;
import android.content.Context;
import android.widget.ProgressBar;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ResultChecker;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Exercise;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.ExerciseFactory;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestBacklog;

/**
 * Created by Alan Meile on 15.05.2017.
 */

public class RPSGame implements Game {
    private ExerciseFactory exerciseFactory;
    private Exercise currentExercise;
    private Context gameView;


    @Override
    public void start(Context gameView) {
        this.gameView = gameView;
        initExerciseFactory();
    }

    private void initExerciseFactory() {
        String[] easyQuestInfos = gameView.getResources().getStringArray(R.array.easyQuestInfos);
        String[] hardQuestInfos = gameView.getResources().getStringArray(R.array.hardQuestInfos);
        exerciseFactory = new ExerciseFactory(new Random(), new QuestBacklog(easyQuestInfos, hardQuestInfos));
    }

    @Override
    public void nextExercise() {
        if (new Random().nextInt(1) == 1) {
            currentExercise = exerciseFactory.hardExercise();
        } else {
            currentExercise = exerciseFactory.easyExercise();
        }
    }

    @Override
    public void skip() {

    }

    @Override
    public void pause() {

    }

    @Override
    public ExerciseResult solveWith(Gesture answer) {
        ProgressBar progressBar = getGameViewProgressBar();
        ResultChecker resultChecker = new ResultChecker(progressBar.getMax());
        ExerciseResult result = resultChecker.checkAnswer(currentExercise.getQuest(), answer, progressBar.getProgress());
        return result;
    }

    public GameSituation getGameSituation() {
        return currentExercise.getGameSituation();
    }

    public Quest getQuest() {
        return currentExercise.getQuest();
    }

    private ProgressBar getGameViewProgressBar() {
        return (ProgressBar) ((Activity) gameView).findViewById(R.id.timeView);
    }
}