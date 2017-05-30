package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Alan Meile on 17.05.2017.
 */

public class PlayerStats {
    private SharedPreferences sharedPreferences;
    private int points;
    private int life;

    public PlayerStats(Context context, int startPoints, int startLife) {
        String packageName = context.getPackageName();
        sharedPreferences = context.getSharedPreferences(packageName, MODE_PRIVATE);
        points = sharedPreferences.getInt("PlayerPoints", startPoints);
        life = sharedPreferences.getInt("PlayerLife", startLife);
    }

    public void awardPoints(int awardedPoints) {
        points += awardedPoints;
        sharedPreferences.edit().putInt("PlayerPoints", points).commit();
    }

    public void deductLife() {
        life--;
        sharedPreferences.edit().putInt("PlayerLife", life).commit();
    }

    public int getPoints() {
        return points;
    }

    public int getLife() {
        return life;
    }
}