package ch.hslu.mobpro.proj.thinkquick.game;

/**
 * Created by Alan Meile on 05.05.2017.
 */

public enum Gesture {
    ROCK(0), PAPER(1), SCISSOR(2), DRAW(3);
    private int value;

    Gesture(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Gesture match(Gesture leftHand, Gesture rightHand) {
        Gesture winner = DRAW;

        if (ROCK.getValue() == leftHand.getValue()) {
            if (SCISSOR.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (PAPER.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        } else if (PAPER.getValue() == leftHand.getValue()) {
            if (ROCK.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (SCISSOR.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        } else if (SCISSOR.getValue() == leftHand.getValue()) {
            if (PAPER.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (ROCK.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        }

        return winner;
    }
}