package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.handler.CountDownBackgroundHandler;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

public class CountdownActivity extends AppCompatActivity {
    private final static int DELAY = 1000;
    private CountDownBackgroundHandler countDownBackgroundHandler;
    private Handler countDown;
    private Intent gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        countDownBackgroundHandler = new CountDownBackgroundHandler(this);
        gameActivity = new Intent(this, GameActivity.class);

        setExercisePoints();
        setExerciseResult();
        startCountDown();
    }

    private void setExerciseResult() {
        countDownBackgroundHandler.setCountDownBackgroundFromAnswer(
                PreferenceSingleton.getHandler(this).getExerciseResult());
    }

    private void setExercisePoints() {
        countDownBackgroundHandler.setCountDownPoints(
                PreferenceSingleton.getHandler(this).getExercisePoints());
    }

    private void startCountDown() {
        countDown = new Handler();
        countDownOneStep();
    }

    private void countDownOneStep() {
        countDown.postDelayed(new Runnable() {
            public void run() {
                startActivity(gameActivity);
            }
        }, DELAY);
    }

    private void cancelCountDown() {
        countDown.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelCountDown();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelCountDown();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownOneStep();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}