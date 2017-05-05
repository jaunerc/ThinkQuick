package ch.hslu.mobpro.proj.thinkquick.game;

import ch.hslu.mobpro.proj.thinkquick.interfaces.InterfaceLife;

/**
 * Created by alkazua on 05.05.2017.
 */

public class Life implements InterfaceLife {
    private int life;
    private boolean isAlive;

    private Life() {}

    public Life(int maxLife, boolean alive) {
        life = maxLife;
        isAlive = alive;
    }

    @Override
    public void removeLife() {
        if (isAlive()) {
            life--;

            if (life == 0) isAlive = false;
        }
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }
}