package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;

/**
 * Created by Alan Meile on 17.05.2017.
 */

public class PlayerStats {
    private final Context context;
    private int life;

    public PlayerStats(Context context) {
        this.context = context;
        life = PreferenceSingleton.getHandler(context).getPlayerLife();
    }

    public void awardPoints(int awardedPoints) {
        PreferenceSingleton.getHandler(context).setPlayerPoints(getPoints() + awardedPoints);
    }

    public void deductLife() {
        life--;
        PreferenceSingleton.getHandler(context).setPlayerLife(life);
    }

    public int getPoints() {
        return PreferenceSingleton.getHandler(context).getPlayerPoints();
    }

    public int getLife() {
        return life;
    }
}