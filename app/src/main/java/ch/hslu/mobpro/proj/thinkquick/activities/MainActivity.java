package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

public class MainActivity extends AppCompatActivity {
    private Context mainAppContext;
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

        checkGodMode();
        resetPlayerStats();
        initUiControls();
    }

    private void checkGodMode() {
        if (PreferenceSingleton.getHandler(mainAppContext).getGodMode()) {
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
                boolean b = PreferenceSingleton.getHandler(mainAppContext).getGodMode();
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
        PreferenceSingleton.getHandler(mainAppContext).setGodMode(false);
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
                    PreferenceSingleton.getHandler(mainAppContext).setGodMode(true);
                    int gold = Color.argb(255, 212, 175, 55);
                    settings.setBackgroundColor(gold);
                }
            }
        });

        builder.show();
    }

    private void resetPlayerStats() {
        PreferenceSingleton.getHandler(mainAppContext).setPlayerPoints(0);
        PreferenceSingleton.getHandler(mainAppContext).setPlayerLife(3);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}