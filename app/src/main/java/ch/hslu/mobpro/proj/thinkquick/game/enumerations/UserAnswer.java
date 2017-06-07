package ch.hslu.mobpro.proj.thinkquick.game.enumerations;

import android.graphics.Color;

/**
 * Created by Alan Meile on 05.06.2017.
 */

public enum UserAnswer {
    CORRECT(Color.GREEN), WRONG(Color.RED), SKIPPED(Color.YELLOW);

    private final int color;

    UserAnswer(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
