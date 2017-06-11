package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * This Activity shows a menu to choose a game mode.
 */
public class GameModeActivity extends AppCompatActivity {

    private final static int ENDLESS_MODE_INDEX = 1;
    private final static int HARDCORE_MODE_INDEX = 2;
    private final static int SURPRISE_MODE_INDEX = 3;
    public final static String GAMEMODE_INDEX = "gamemode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);
    }

    public void onEndlessButtonClick(final View view) {
        startGameActivity(ENDLESS_MODE_INDEX);
    }

    public void onHardcoreButtonClick(final View view) {
        startGameActivity(HARDCORE_MODE_INDEX);
    }

    public void onSurpriseButtonClick(final View view) {
        startGameActivity(SURPRISE_MODE_INDEX);
    }

    private void startGameActivity(final int modeIndex) {
        final Intent gameIntent = new Intent(this, GameActivity.class);
        gameIntent.putExtra(GAMEMODE_INDEX, modeIndex);
        startActivity(gameIntent);
    }
}
