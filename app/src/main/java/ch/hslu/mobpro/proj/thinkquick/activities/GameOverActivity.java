package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.database.DbAdapter;
import ch.hslu.mobpro.proj.thinkquick.database.DbResultsEntry;
import ch.hslu.mobpro.proj.thinkquick.game.helper.PlayerStats;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

public class GameOverActivity extends AppCompatActivity {
    private static final int MAINACTIVITY_DELAY = 2000;

    private Intent mainActivity;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        dbAdapter = new DbAdapter(this);
        mainActivity = new Intent(this, MainActivity.class);

        TextView playerPoints = (TextView) findViewById(R.id.gameOverPoints);
        PlayerStats playerStats = new PlayerStats(this);
        playerPoints.setText(getString(R.string.gameover_points_description) + " " + playerStats.getPoints());
        String gameModeLabel = PreferenceSingleton.getHandler(this).getGameModeForDb();

        saveResultOnDB(playerStats.getPoints(), gameModeLabel);
        testGet();

        final Context context = this;
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

    private void saveResultOnDB(final int points, final String mode) {
        dbAdapter.open();
        try {
            dbAdapter.insert(points, new Date(), mode);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error while writing to db...", Toast.LENGTH_SHORT);
        } finally {
            dbAdapter.close();
        }
    }

    private void testGet() {
        dbAdapter.open();
        try {
            List<DbResultsEntry> res = dbAdapter.getAllResults();
            for (DbResultsEntry entry : res) {
                System.out.println("result: " + entry.getDate() + ", " + entry.getPoints());
            }
            System.out.println("res size " + res.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}