package ch.hslu.mobpro.proj.thinkquick.game.exercises;

import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;

/**
 * This class represents a quest.
 */

public class Quest {
    private String info;
    private Gesture answer;
    private QuestTarget questTarget;
    private boolean againstWinner;

    public Quest(String info, Gesture answer, QuestTarget questTarget, boolean againstWinner) {
        this.info = info;
        this.answer = answer;
        this.questTarget = questTarget;
        this.againstWinner = againstWinner;
    }

    public Quest(String info, QuestTarget questTarget, boolean againstWinner) {
        this(info, null, questTarget, againstWinner);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Gesture getAnswer() {
        return answer;
    }

    public void setAnswer(Gesture answer) {
        this.answer = answer;
    }

    public QuestTarget getQuestTarget() {
        return questTarget;
    }

    public void setQuestTarget(QuestTarget questTarget) {
        this.questTarget = questTarget;
    }

    public boolean isAgainstWinner() {
        return againstWinner;
    }

    public void setAgainstWinner(boolean againstWinner) {
        this.againstWinner = againstWinner;
    }
}