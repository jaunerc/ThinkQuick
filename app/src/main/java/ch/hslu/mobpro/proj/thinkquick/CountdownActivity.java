package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CountdownActivity extends AppCompatActivity {
    private final static int COUNTDOWNINMILLIS = 4000;
    private final static int INTERVALINMILLIS = 1000;
    private TextView countdownView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        startCountDown();
    }

    private void startCountDown() {
        countdownView = (TextView) findViewById(R.id.countdownView);
        final Intent gameActivity = new Intent(this, GameActivity.class);

        countDownTimer = new CountDownTimer(COUNTDOWNINMILLIS, INTERVALINMILLIS) {
            @Override
            public void onTick(long millisUntilFinished) {
                int count = (int) (millisUntilFinished / INTERVALINMILLIS);
                countdownView.setText(Integer.toString(count));
            }

            @Override
            public void onFinish() {
                startActivity(gameActivity);
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
