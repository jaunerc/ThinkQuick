package ch.hslu.mobpro.proj.thinkquick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import ch.hslu.mobpro.proj.thinkquick.game.checker.PointCalculator;

public class PointsDistributionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_distribution);

        plotGraph();
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
}
