package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.os.Handler;
import android.widget.ProgressBar;

import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;

/**
 * Created by Alan Meile on 18.05.2017.
 */

public class ProgressTime {
    private final static int DELAY = 60;
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

    public String execute(int maxProgress) {
        initAfterCountDownExecution();
        startCountDown(maxProgress);

        return null;
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
        }, DELAY);
    }

    private void publishProgress() {
        progressBar.setProgress(displayProgress);
    }

    public void cancel() {
        countDown.removeCallbacksAndMessages(null);
    }
}