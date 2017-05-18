package ch.hslu.mobpro.proj.thinkquick.game;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by Alan Meile on 18.05.2017.
 */

public class ProgressTime extends AsyncTask<Integer, Integer, String> {
    private final static int INTERVAL = 40;
    private RPSGame rpsGame;
    private ProgressBar progressBar;

    private ProgressTime() {
    }

    public ProgressTime(ProgressBar progressBar, RPSGame rpsGame) {
        this.rpsGame = rpsGame;
        this.progressBar = progressBar;
    }

    @Override
    protected String doInBackground(Integer... params) {
        for (int count = params[0] - 1; count >= params[1]; count--) {
            try {
                Thread.sleep(INTERVAL);
                publishProgress(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        rpsGame.timeUp();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }
}