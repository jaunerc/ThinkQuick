package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {
    private final static int DELAY = 1000;
    private int count = 3;
    private TextView countdownView;
    private SharedPreferences sharedPreferences;
    private Runnable performAfterExecution;
    private Handler countDown;
    private Intent gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        countdownView = (TextView) findViewById(R.id.countdownView);
        gameActivity = new Intent(this, GameActivity.class);

        initAfterCountDownExecution();
        setupSharedPreferences();
        setExerciseResult();
        startCountDown();
    }

    private void initAfterCountDownExecution() {
        countdownView.setText(Integer.toString(count));
        performAfterExecution = new Runnable() {
            public void run() {
                count--;
                if (count > 0) {
                    countdownView.setText(Integer.toString(count));
                } else {
                    countdownView.setText(R.string.countdown_go);
                }
                countDownOneStep();
            }
        };
    }

    private void setupSharedPreferences() {
        String packageName = getApplicationContext().getPackageName();
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
    }

    private void setExerciseResult() {
        TextView exerciseResult = (TextView) findViewById(R.id.countdownResult);
        TextView countDownPoints = (TextView) findViewById(R.id.countDownPoints);

        int points = sharedPreferences.getInt("ExercisePoints", 0);

        if (points >= 0) {
            countDownPoints.setText("+ " + Integer.toString(points));
        } else {
            countDownPoints.setText("- " + Integer.toString(-1 * points));
        }


        if (sharedPreferences.getBoolean("ExerciseCorrect", false)) {
            exerciseResult.setText(R.string.result_correct);
        } else {
            exerciseResult.setText(R.string.result_wrong);
        }

    }

    private void startCountDown() {
        countDown = new Handler();
        countDownOneStep();
    }

    private void countDownOneStep() {
        countDown.postDelayed(new Runnable() {
            public void run() {
                if (count > 0) {
                    countDown.postDelayed(performAfterExecution, 0);
                } else {
                    startActivity(gameActivity);
                }
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