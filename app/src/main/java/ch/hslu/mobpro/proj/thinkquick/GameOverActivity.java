package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.game.PlayerStats;

public class GameOverActivity extends AppCompatActivity {

    private Intent mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        mainActivity = new Intent(this, MainActivity.class);

        TextView playerPoints = (TextView) findViewById(R.id.gameOverPoints);
        playerPoints.setText("Points: " + new PlayerStats(this, 0, 3).getPoints());

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.gameOverLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(mainActivity);
            }
        });
    }
}
