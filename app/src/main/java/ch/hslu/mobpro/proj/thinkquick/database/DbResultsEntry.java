package ch.hslu.mobpro.proj.thinkquick.database;

/**
 * This class represents a single entry in the database.
 */

public class DbResultsEntry {
    private int points;
    private String date;
    private String mode;

    public DbResultsEntry(int points, String date, String mode) {
        this.points = points;
        this.date = date;
        this.mode = mode;
    }

    public DbResultsEntry() {
        this(0, null, "");
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
