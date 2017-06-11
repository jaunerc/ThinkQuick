package ch.hslu.mobpro.proj.thinkquick.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;

import java.util.Random;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.activities.CountdownActivity;
import ch.hslu.mobpro.proj.thinkquick.activities.GameOverActivity;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ResultChecker;
import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.enumerations.UserAnswer;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Exercise;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.ExerciseFactory;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.QuestBacklog;
import ch.hslu.mobpro.proj.thinkquick.game.helper.PlayerStats;
import ch.hslu.mobpro.proj.thinkquick.game.helper.ProgressTime;
import ch.hslu.mobpro.proj.thinkquick.game.mode.GameModeStrategy;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

/**
 * Created by Alan Meile on 15.05.2017.
 */

public class RPSGame {
    private final static int MAX_PROGRESS = 100;
    private int progressPausedAt;
    private ProgressTime progressTime;
    private PlayerStats playerStats;
    private ExerciseFactory exerciseFactory;
    private Exercise currentExercise;
    private Context gameView;
    private ProgressBar progressBar;
    private GameModeStrategy gameMode;

    public void start(final Context gameView, final GameModeStrategy gameMode) {
        this.gameView = gameView;
        this.gameMode = gameMode;
        playerStats = new PlayerStats(gameView);
        progressBar = (ProgressBar) ((Activity) gameView).findViewById(R.id.timeView);
        initExerciseFactory();
        initGameMode();
    }

    private void initExerciseFactory() {
        QuestBacklog questBacklog = new QuestBacklog();
        questBacklog.initQuests(gameView);
        exerciseFactory = new ExerciseFactory(new Random(), questBacklog);
    }

    private void initGameMode() {
        if(gameMode != null) {
            gameMode.init();
        }
    }

    public void nextExercise() {
        prepareNextExercise();
        startProgressCountDown();
        if (new Random().nextInt(5) >= 2) {
            currentExercise = exerciseFactory.hardExercise();
        } else {
            currentExercise = exerciseFactory.easyExercise();
        }
    }

    private void prepareNextExercise() {
        if(gameMode != null) {
            gameMode.updateGameforNextExercise(gameView);
        }
    }

    private void startProgressCountDown() {
        if (!PreferenceSingleton.getHandler(gameView).getGodMode()) {
            progressBar.setMax(MAX_PROGRESS);
            progressTime = new ProgressTime(progressBar, this);
            progressTime.execute(MAX_PROGRESS);
        }
    }

    private void startProgressCountDown(int currentProgress) {
        if (!PreferenceSingleton.getHandler(gameView).getGodMode()) {
            progressBar.setMax(MAX_PROGRESS);
            progressTime = new ProgressTime(progressBar, this);
            progressTime.execute(currentProgress, false);
        }
    }

    public void gameOver() {
        Intent gameOver = new Intent(gameView, GameOverActivity.class);
        gameView.startActivity(gameOver);
    }

    public void skip() {
        stopProgressBarTask();
        playerStats.awardPoints(-100);
        PreferenceSingleton.getHandler(gameView).setExerciseResult(UserAnswer.SKIPPED);
        showCountDownActivity();
    }

    public void pause() {
        stopProgressBarTask();
        progressPausedAt = progressBar.getProgress();
    }

    public void stop() {
        stopProgressBarTask();
    }

    public void resume() {
        startProgressCountDown(progressPausedAt);
    }

    public void timeUp() {
        deductPlayerLife();
    }

    public ExerciseResult solveWith(Gesture answer) {
        stopProgressBarTask();
        ProgressBar progressBar = getGameViewProgressBar();
        ResultChecker resultChecker = new ResultChecker(progressBar.getMax());
        ExerciseResult result = resultChecker.checkAnswer(currentExercise.getQuest(), answer, progressBar.getProgress());
        return result;
    }

    private void stopProgressBarTask() {
        if (!PreferenceSingleton.getHandler(gameView).getGodMode()) {
            progressTime.cancel();
        }
    }

    public void deductPlayerLife() {
        if (playerStats.getLife() > 0) {
            playerStats.deductLife();
            PreferenceSingleton.getHandler(gameView).setExercisePoints(0);
            PreferenceSingleton.getHandler(gameView).setExerciseResult(UserAnswer.WRONG);
            showCountDownActivity();
        } else {
            gameOver();
        }
    }

    private void showCountDownActivity() {
        Intent countDownActivity = new Intent(gameView, CountdownActivity.class);
        gameView.startActivity(countDownActivity);
    }

    public int getPlayerLife() {
        return playerStats.getLife();
    }

    public void awardPlayerPoints(int awardedPoints) {
        playerStats.awardPoints(awardedPoints);
        showCountDownActivity();
    }

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

    public void orientationChanged(int currentProgress) {
        stopProgressBarTask();
        startProgressCountDown(currentProgress);
    }

    public GameModeStrategy getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameModeStrategy gameMode) {
        this.gameMode = gameMode;
    }
}