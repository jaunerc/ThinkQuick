package ch.hslu.mobpro.proj.thinkquick.game.mode;

/**
 * This class represents a data model for the game config.
 */

public class GameConfig {
    private int maxProgress;
    private int startPoints;
    private int startLife;

    public GameConfig() {
        this(0, 0, 0);
    }

    public GameConfig(int maxProgress, int startPoints, int startLife) {
        this.maxProgress = maxProgress;
        this.startPoints = startPoints;
        this.startLife = startLife;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getStartPoints() {
        return startPoints;
    }

    public int getStartLife() {
        return startLife;
    }
}
