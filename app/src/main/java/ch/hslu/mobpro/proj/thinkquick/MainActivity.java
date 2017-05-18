package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final static boolean TEST_MODE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (TEST_MODE == true) {
            runTestMode();
        }

        final Intent gameActivity = new Intent(this, GameActivity.class);
        final Intent hightscoreActivity = new Intent(this, HighscoreActivity.class);

        Button play = (Button) findViewById(R.id.mainPlay);
        Button highscore = (Button) findViewById(R.id.mainHighscore);

        resetPlayerStats();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(gameActivity);
                finish();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(hightscoreActivity);
                finish();
            }
        });
    }

    private void resetPlayerStats() {
        String packageName = getPackageName();
        SharedPreferences sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
        sharedPreferences.edit().putInt("PlayerPoints", 0).commit();
        sharedPreferences.edit().putInt("PlayerLife", 3).commit();
    }

    private void runTestMode() {
        String packageName = getApplicationContext().getPackageName();

        SharedPreferences sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("firstrun", true).commit();
    }
}