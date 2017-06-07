package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import ch.hslu.mobpro.proj.thinkquick.R;

public class SplashActivity extends AppCompatActivity {
    private static final int MAINACTIVITY_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startMainActivityDelayed(this);
    }

    private void startMainActivityDelayed(final Context context) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, MAINACTIVITY_DELAY);
    }
}
