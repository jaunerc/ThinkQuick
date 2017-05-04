package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent gameActivity = new Intent(this, GameActivity.class);
        final Intent hightscoreActivity = new Intent(this, HighscoreActivity.class);

        Button play = (Button) findViewById(R.id.mainPlay);
        Button highscore = (Button) findViewById(R.id.mainHighscore);

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
}