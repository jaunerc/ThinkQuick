package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

public class CountdownActivity extends AppCompatActivity {
    private final static int DELAY = 1000;
    private Handler countDown;
    private Intent gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        gameActivity = new Intent(this, GameActivity.class);

        setExerciseResult();
        startCountDown();
    }

    private void setExerciseResult() {
        TextView countDownPoints = (TextView) findViewById(R.id.countDownPoints);
        int points = PreferenceSingleton.getHandler(this).getExercisePoints();

        if (points >= 0) {
            countDownPoints.setText("+ " + Integer.toString(points));
        } else {
            countDownPoints.setText("- " + Integer.toString(-1 * points));
        }


        /*if (PreferenceSingleton.getHandler(this).getExerciseResult()) {

        } else {

        }*/

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
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}