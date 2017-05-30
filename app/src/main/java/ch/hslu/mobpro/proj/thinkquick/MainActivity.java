package ch.hslu.mobpro.proj.thinkquick;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Context mainAppContext;
    private SharedPreferences sharedPreferences;
    private Button settings;
    private Button play;
    private Button highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.mainPlay);
        highscore = (Button) findViewById(R.id.mainHighscore);
        settings = (Button) findViewById(R.id.mainMenuSettings);

        mainAppContext = this;

        setupSharedPreferences();
        checkGodMode();
        resetPlayerStats();
        initUiControls();
    }

    private void checkGodMode() {
        if (sharedPreferences.getBoolean("GodMode", false)) {
            int gold = Color.argb(255, 212, 175, 55);
            settings.setBackgroundColor(gold);
        }
    }

    private void initUiControls() {
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent gameActivity = new Intent(mainAppContext, GameActivity.class);
                startActivity(gameActivity);
                finish();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent hightscoreActivity = new Intent(mainAppContext, HighscoreActivity.class);
                startActivity(hightscoreActivity);
                finish();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent settingActivity = new Intent(mainAppContext, PointsDistributionActivity.class);
                startActivity(settingActivity);
                finish();
            }
        });

        settings.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                boolean b = sharedPreferences.getBoolean("GodMode", false);
                if (!b) {
                    startGodMode();
                } else {
                    endGodMode();
                }

                return false;
            }
        });
    }

    private void endGodMode() {
        sharedPreferences.edit().putBoolean("GodMode", false).commit();
    }

    private void setupSharedPreferences() {
        String packageName = getPackageName();
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
    }

    private void startGodMode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainAppContext);
        builder.setTitle("Enter God Mode");

        final EditText input = new EditText(mainAppContext);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            String m_Text;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                if (m_Text.equals("MobPro")) {
                    sharedPreferences.edit().putBoolean("GodMode", true).commit();
                    int gold = Color.argb(255, 212, 175, 55);
                    settings.setBackgroundColor(gold);
                }
            }
        });

        builder.show();
    }

    private void resetPlayerStats() {
        sharedPreferences.edit().putInt("PlayerPoints", 0).commit();
        sharedPreferences.edit().putInt("PlayerLife", 3).commit();
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}