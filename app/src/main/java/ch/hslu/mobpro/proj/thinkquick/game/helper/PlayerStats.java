package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

/**
 * Created by Alan Meile on 17.05.2017.
 */

public class PlayerStats {
    private final Context context;
    private int points;
    private int life;

    public PlayerStats(Context context, int startPoints, int startLife) {
        this.context = context;
        points = PreferenceSingleton.getHandler(context).getPlayerPoints();
        life = PreferenceSingleton.getHandler(context).getPlayerLife();
    }

    public void awardPoints(int awardedPoints) {
        points += awardedPoints;
        PreferenceSingleton.getHandler(context).setPlayerPoints(awardedPoints);
    }

    public void deductLife() {
        life--;
        PreferenceSingleton.getHandler(context).setPlayerLife(life);
    }

    public int getPoints() {
        return points;
    }

    public int getLife() {
        return life;
    }
}