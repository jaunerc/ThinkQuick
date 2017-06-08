package ch.hslu.mobpro.proj.thinkquick.game.enumerations;

/**
 * Created by Alan Meile on 05.06.2017.
 */

public enum UserAnswer {
    DEFAULT(0), CORRECT(1), WRONG(2), SKIPPED(3);

    private final int value;

    UserAnswer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
