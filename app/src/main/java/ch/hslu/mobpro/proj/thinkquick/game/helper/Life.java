package ch.hslu.mobpro.proj.thinkquick.game.helper;

/**
 * Created by alkazua on 05.05.2017.
 */

public class Life {
    private int life;
    private boolean isAlive;

    private Life() {}

    public Life(int maxLife, boolean alive) {
        life = maxLife;
        isAlive = alive;
    }

    public void removeLife() {
        if (isAlive()) {
            life--;

            if (life == 0) isAlive = false;
        }
    }

    public int getLife() {
        return life;
    }

    public boolean isAlive() {
        return isAlive;
    }
}