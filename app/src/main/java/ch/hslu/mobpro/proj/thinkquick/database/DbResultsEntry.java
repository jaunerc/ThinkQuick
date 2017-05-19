package ch.hslu.mobpro.proj.thinkquick.database;

/**
 * This class represents a single entry in the database.
 */

public class DbResultsEntry {
    private int points;
    private String date;

    public DbResultsEntry(int points, String date) {
        this.points = points;
        this.date = date;
    }

    public DbResultsEntry() {
        this(0, null);
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
}
