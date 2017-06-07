package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.database.DbAdapter;
import ch.hslu.mobpro.proj.thinkquick.database.DbResultsEntry;

public class HighscoreActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        dbAdapter = new DbAdapter(this);
        ListView listView = (ListView) findViewById(R.id.highscoreList);

        String[] values = loadResultFromDB();
        if (values == null) {
            values = new String[]{"No Highscore available"};
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(arrayAdapter);
    }

    private String[] loadResultFromDB() {
        String[] values = null;
        dbAdapter.open();
        try {
            List listResults = dbAdapter.getAllResults();
            values = extractStrings(listResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

    private String[] extractStrings(List listResults) {
        int index = 0;
        String[] values = new String[listResults.size()];
        for (Object dbResultsEntry : listResults) {
            int points = ((DbResultsEntry) dbResultsEntry).getPoints();
            String date = ((DbResultsEntry) dbResultsEntry).getDate();
            values[index] = date + "\t\t\t\t\t\t" + points;
            index++;
        }

        return values;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
