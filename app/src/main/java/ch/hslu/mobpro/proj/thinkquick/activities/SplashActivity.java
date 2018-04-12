package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ch.hslu.mobpro.proj.thinkquick.R;

public class SplashActivity extends AppCompatActivity {
    private static final int MAINACTIVITY_DELAY = 4000;
    private Handler handler;
    private Runnable startMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startMainActivityDelayed(this);
    }

    private void startMainActivityDelayed(final Context context) {
        handler = new Handler();
        startMain = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(startMain, MAINACTIVITY_DELAY);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(startMain);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(startMain, MAINACTIVITY_DELAY);
    }
}
