package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.os.Handler;
import android.widget.ProgressBar;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

/**
 * Created by Alan Meile on 18.05.2017.
 */

public class ProgressTime {
    private final static int COUNTDOWN_DELAY = 2000;
    private final static int TICK_DELAY = 20;
    private Handler countDown;
    private RPSGame rpsGame;
    private ProgressBar progressBar;
    private Runnable performAfterExecution;
    private int displayProgress;

    private ProgressTime() {
    }

    public ProgressTime(ProgressBar progressBar, RPSGame rpsGame) {
        this.rpsGame = rpsGame;
        this.progressBar = progressBar;
    }

    private void initAfterCountDownExecution() {
        performAfterExecution = new Runnable() {
            public void run() {
                startCountDown(displayProgress);
            }
        };
    }

    public void execute(final int maxProgress) {
        initAfterCountDownExecution();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startCountDown(maxProgress);
            }
        }, COUNTDOWN_DELAY);
    }

    public void execute(final int maxProgress, final boolean delayed) {
        initAfterCountDownExecution();
        startCountDown(maxProgress);
    }

    private void startCountDown(final int maxProgress) {
        countDown = new Handler();
        countDown.postDelayed(new Runnable() {
            public void run() {
                if (maxProgress > 0) {
                    displayProgress = maxProgress - 1;
                    publishProgress();
                    countDown.postDelayed(performAfterExecution, 0);
                } else {
                    rpsGame.timeUp();
                }
            }
        }, TICK_DELAY);
    }

    private void publishProgress() {
        progressBar.setProgress(displayProgress);
    }

    public void cancel() {
        countDown.removeCallbacksAndMessages(null);
    }
}