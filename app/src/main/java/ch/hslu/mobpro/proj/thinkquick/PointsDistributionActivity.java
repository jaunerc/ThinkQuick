package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import ch.hslu.mobpro.proj.thinkquick.database.DbAdapter;
import ch.hslu.mobpro.proj.thinkquick.game.checker.PointCalculator;

public class PointsDistributionActivity extends AppCompatActivity {
    private CheckBox tutorialBox;
    private SharedPreferences sharedPreferences;
    private Button backToMenu;
    private Intent mainActivity;
    private CheckBox highscoreBox;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_distribution);

        mainActivity = new Intent(this, MainActivity.class);

        dbAdapter = new DbAdapter(this);
        tutorialBox = (CheckBox) findViewById(R.id.checkBoxTutorial);
        highscoreBox = (CheckBox) findViewById(R.id.deleteAllContent);
        backToMenu = (Button) findViewById(R.id.buttonBackToMain);

        setupSharedPreferences();
        setupControls();
        loadSettings();
        plotGraph();
    }

    private void setupSharedPreferences() {
        String packageName = getApplicationContext().getPackageName();
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
    }

    private void setupControls() {
        tutorialBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = tutorialBox.isChecked();
                sharedPreferences.edit().putBoolean("firstrun", isChecked).commit();
            }
        });

        highscoreBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dbAdapter.open();
                dbAdapter.clearAllContent();
            }
        });

        backToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(mainActivity);
                finish();
            }
        });
    }

    private void loadSettings() {
        boolean isTutorialWished = sharedPreferences.getBoolean("firstrun", true);
        setupCheckBox(isTutorialWished);
    }

    private void setupCheckBox(boolean isTutorialWished) {
        tutorialBox.setChecked(isTutorialWished);
    }

    private void plotGraph() {
        GraphView graph = (GraphView) findViewById(R.id.pointsGraph);
        DataPoint[] pointSerie = new DataPoint[100];

        for (int i = 0; i < 100; i++) {
            pointSerie[i] = new DataPoint(i, PointCalculator.calcPoints(100 - i, 100));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointSerie);
        graph.addSeries(series);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
