package ch.hslu.mobpro.proj.thinkquick.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.database.DbAdapter;
import ch.hslu.mobpro.proj.thinkquick.game.checker.PointCalculator;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

public class PointsDistributionActivity extends AppCompatActivity {
    private CheckBox tutorialBox;
    private Button backToMenu;
    private Intent mainActivity;
    private CheckBox highscoreBox;
    private DbAdapter dbAdapter;
    private PointsDistributionActivity pointsDistributionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_distribution);

        mainActivity = new Intent(this, MainActivity.class);
        pointsDistributionActivity = this;

        dbAdapter = new DbAdapter(this);
        tutorialBox = (CheckBox) findViewById(R.id.checkBoxTutorial);
        highscoreBox = (CheckBox) findViewById(R.id.deleteAllContent);
        backToMenu = (Button) findViewById(R.id.buttonBackToMain);

        setupControls();
        loadTutorialSettings();
        plotPointsGraph();
    }

    private void setupControls() {
        tutorialBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isChecked = tutorialBox.isChecked();
                PreferenceSingleton.getHandler(pointsDistributionActivity).setFirstRun(isChecked);
            }
        });

        highscoreBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(PointsDistributionActivity.this).create();
                alertDialog.setTitle(R.string.delete_highscore_title);

                alertDialog.setMessage(getString(R.string.delete_highscore));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.delete_approve),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dbAdapter.open();
                                dbAdapter.clearAllContent();
                                dialog.dismiss();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.delete_deny),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                highscoreBox.setChecked(false);
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
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

    private void loadTutorialSettings() {
        boolean isTutorialWished = PreferenceSingleton.getHandler(pointsDistributionActivity).getFirstRun();
        setupCheckBox(isTutorialWished);
    }

    private void setupCheckBox(boolean isTutorialWished) {
        tutorialBox.setChecked(isTutorialWished);
    }

    private void plotPointsGraph() {
        GraphView graph = (GraphView) findViewById(R.id.pointsGraph);
        DataPoint[] pointSerie = new DataPoint[100];

        for (int i = 0; i < 100; i++) {
            pointSerie[i] = new DataPoint(i, PointCalculator.calcPoints(100 - i, 100, 100));
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(pointSerie);
        graph.addSeries(series);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
