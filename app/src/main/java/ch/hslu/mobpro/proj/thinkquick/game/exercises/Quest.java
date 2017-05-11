package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public class Quest {
    private String info;
    private Gesture answer;

    private Quest() {}

    /**
     * Create a quest, that the player has to solve
     * @param questInfo The quest that needs to be solved
     * @param questAnswer The correct answer to the quest
     */
    public Quest(String questInfo, Gesture questAnswer) {
        info = questInfo;
        answer = questAnswer;
    }

    public String getInfo() {
        return info;
    }

    public Gesture getAnswer() {
        return answer;
    }
}