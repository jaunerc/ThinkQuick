package ch.hslu.mobpro.proj.thinkquick.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.CountdownActivity;
import ch.hslu.mobpro.proj.thinkquick.GameOverActivity;
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
    private final static int MAX_PROGRESS = 100;
    private final static int MIN_PROGRESS = 0;
    private final static int START_POINTS = 0;
    private final static int START_LIFE = 3;
    private AsyncTask<Integer, Integer, String> progressTime;
    private PlayerStats playerStats;
    private ExerciseFactory exerciseFactory;
    private Exercise currentExercise;
    private Context gameView;


    @Override
    public void start(Context gameView) {
        this.gameView = gameView;
        playerStats = new PlayerStats(gameView, START_POINTS, START_LIFE);
        initExerciseFactory();
    }

    private void initExerciseFactory() {
        QuestBacklog questBacklog = new QuestBacklog();
        questBacklog.initQuests(gameView);
        exerciseFactory = new ExerciseFactory(new Random(), questBacklog);
    }

    @Override
    public void nextExercise() {
        startProgressCountDown();
        if (new Random().nextInt(5) >= 2) {
            currentExercise = exerciseFactory.hardExercise();
        } else {
            currentExercise = exerciseFactory.easyExercise();
        }
    }

    private void startProgressCountDown() {
        ProgressBar progressBar = (ProgressBar) ((Activity) gameView).findViewById(R.id.timeView);
        progressTime = new ProgressTime(progressBar, this).execute(MAX_PROGRESS, MIN_PROGRESS);
    }

    @Override
    public void gameOver() {
        Intent gameOver = new Intent(gameView, GameOverActivity.class);
        gameView.startActivity(gameOver);
    }

    @Override
    public void skip() {
        stopProgressBarTask();
        playerStats.awardPoints(-100);
        showCountDownActivity();
    }

    @Override
    public void pause() {

    }

    @Override
    public void timeUp() {
        deductPlayerLife();
    }

    @Override
    public ExerciseResult solveWith(Gesture answer) {
        stopProgressBarTask();
        ProgressBar progressBar = getGameViewProgressBar();
        ResultChecker resultChecker = new ResultChecker(progressBar.getMax());
        ExerciseResult result = resultChecker.checkAnswer(currentExercise.getQuest(), answer, progressBar.getProgress());
        return result;
    }

    private void stopProgressBarTask() {
        progressTime.cancel(true);
    }

    @Override
    public void deductPlayerLife() {
        if (playerStats.getLife() > 0) {
            playerStats.deductLife();
            showCountDownActivity();
        } else {
            gameOver();
        }
    }

    private void showCountDownActivity() {
        Intent countDownActivity = new Intent(gameView, CountdownActivity.class);
        gameView.startActivity(countDownActivity);
    }

    @Override
    public int getPlayerLife() {
        return playerStats.getLife();
    }

    @Override
    public void awardPlayerPoints(int awardedPoints) {
        playerStats.awardPoints(awardedPoints);
        showCountDownActivity();
    }

    @Override
    public int getPlayerPoints() {
        return playerStats.getPoints();
    }

    public GameSituation getGameSituation() {
        if (currentExercise == null) nextExercise();
        return currentExercise.getGameSituation();
    }

    public Quest getQuest() {
        return currentExercise.getQuest();
    }

    private ProgressBar getGameViewProgressBar() {
        return (ProgressBar) ((Activity) gameView).findViewById(R.id.timeView);
    }
}