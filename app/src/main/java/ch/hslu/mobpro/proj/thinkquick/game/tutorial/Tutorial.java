package ch.hslu.mobpro.proj.thinkquick.game.tutorial;

/**
 * Created by Alan Meile on 10.05.2017.
 */

public class Tutorial {
    private int id;
    private int topic;
    private int description;

    private Tutorial(){};

    public Tutorial(int id, int topic, int description) {
        setId(id);
        setTopic(topic);
        setDescription(description);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getTopic() {
        return topic;
    }

    public int getDescription() {
        return description;
    }
}
