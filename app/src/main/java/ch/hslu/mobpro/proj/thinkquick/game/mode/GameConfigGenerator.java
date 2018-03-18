package ch.hslu.mobpro.proj.thinkquick.game.mode;

import android.content.Context;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * This class represents a generator for the different game modes.
 */

public class GameConfigGenerator {

    private Context context;

    public GameConfigGenerator(final Context context) {
        this.context = context;
    }

    public GameConfig makeEndlessConfig() {
        return makeConfig(R.integer.endless_maxprogress, R.integer.endless_startpoints, R.integer.endless_startlife);
    }

    public GameConfig makeHardcoreConfig() {
        return makeConfig(R.integer.hardcore_maxprogress, R.integer.hardcore_startpoints, R.integer.hardcore_startlife);
    }

    public GameConfig makeSurpriseConfig() {
        return makeConfig(R.integer.surprise_maxprogress, R.integer.surprise_startpoints, R.integer.surprise_startlife);
    }

    private GameConfig makeConfig(final int progressId, final int pointsId, final int lifeId) {
        final int maxProgress = context.getResources().getInteger(progressId);
        final int startPoints = context.getResources().getInteger(pointsId);
        final int startLife = context.getResources().getInteger(lifeId);
        return new GameConfig(maxProgress, startPoints, startLife);
    }
}
