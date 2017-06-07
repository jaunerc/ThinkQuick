package ch.hslu.mobpro.proj.thinkquick.game.helper;

import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;

/**
 * Created by Alan Meile on 12.05.2017.
 */

public class MatchResult {
    private Gesture winner;
    private boolean isDraw;

    public Gesture getWinner() {
        return winner;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void match(Gesture leftHand, Gesture rightHand) {
        checkWhoWon(leftHand, rightHand);
        checkIfIsDraw();
    }

    private void checkIfIsDraw() {
        isDraw = winner == null;
    }

    private void checkWhoWon(Gesture leftHand, Gesture rightHand) {
        winner = null;

        if (Gesture.ROCK.getValue() == leftHand.getValue()) {
            if (Gesture.SCISSOR.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (Gesture.PAPER.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        } else if (Gesture.PAPER.getValue() == leftHand.getValue()) {
            if (Gesture.ROCK.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (Gesture.SCISSOR.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        } else if (Gesture.SCISSOR.getValue() == leftHand.getValue()) {
            if (Gesture.PAPER.getValue() == rightHand.getValue()) {
                winner = leftHand;
            } else if (Gesture.ROCK.getValue() == rightHand.getValue()) {
                winner = rightHand;
            }
        }
    }
}