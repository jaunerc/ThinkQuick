package ch.hslu.mobpro.proj.thinkquick.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import ch.hslu.mobpro.proj.thinkquick.game.enumerations.UserAnswer;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Alan Meile on 05.06.2017.
 */

public class PreferenceHandler {
    private Context context;
    private SharedPreferences sharedPreferences;

    public PreferenceHandler(Context context) {
        this.context = context;
        setupSharedPreferences();
    }

    private void setupSharedPreferences() {
        String packageName = context.getApplicationContext().getPackageName();
        sharedPreferences = context.getSharedPreferences(packageName, MODE_PRIVATE);
    }

    public boolean getOnPausedProgress() {
        return sharedPreferences.getBoolean("onPausedProgress", false);
    }

    public void setOnPausedProgress(boolean isPaused) {
        sharedPreferences.edit().putBoolean("onPausedProgress", isPaused).commit();
    }

    public int getExercisePoints() {
        return sharedPreferences.getInt("ExercisePoints", 0);
    }

    public void setExercisePoints(int points) {
        sharedPreferences.edit().putInt("ExercisePoints", points).commit();
    }

    public int getExerciseResult() {
        return sharedPreferences.getInt("ExerciseResult", 0);
    }

    public void setExerciseResult(UserAnswer userAnswer) {
        sharedPreferences.edit().putInt("ExerciseResult", userAnswer.getValue()).commit();
    }

    public boolean getFirstRun() {
        return sharedPreferences.getBoolean("firstRun", true);
    }

    public void setFirstRun(boolean isFirstRun) {
        sharedPreferences.edit().putBoolean("firstRun", isFirstRun).commit();
    }

    public boolean getGodMode() {
        return sharedPreferences.getBoolean("GodMode", false);
    }

    public void setGodMode(boolean godMode) {
        sharedPreferences.edit().putBoolean("GodMode", godMode).commit();
    }

    public int getPlayerPoints() {
        return sharedPreferences.getInt("PlayerPoints", 0);
    }

    public void setPlayerPoints(int playerPoints) {
        sharedPreferences.edit().putInt("PlayerPoints", playerPoints).commit();
    }

    public int getPlayerLife() {
        return sharedPreferences.getInt("PlayerLife", 3);
    }

    public void setPlayerLife(int playerLife) {
        sharedPreferences.edit().putInt("PlayerLife", playerLife).commit();
    }

    public int getCurrentProgress() {
        return sharedPreferences.getInt("CurrentProgress", 0);
    }

    public void setCurrentProgress(int progress) {
        sharedPreferences.edit().putInt("CurrentProgress", progress).commit();
    }

    public boolean getOnBackPressed() {
        return sharedPreferences.getBoolean("BackPressed", false);
    }

    public void setOnBackPressed(boolean onBackPressed) {
        sharedPreferences.edit().putBoolean("BackPressed", onBackPressed).commit();
    }

    public void setGameModeForDb(String gameModeName) {
        sharedPreferences.edit().putString("GameModeDb", "endless");
    }

    public String getGameModeForDb() {
        return sharedPreferences.getString("GameModeDb", "endless");
    }
}