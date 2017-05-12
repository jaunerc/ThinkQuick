package ch.hslu.mobpro.proj.thinkquick.game;

/**
 * Created by Alan Meile on 05.05.2017.
 */

public enum Gesture {
    ROCK(0), PAPER(1), SCISSOR(2);
    private int value;

    Gesture(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}