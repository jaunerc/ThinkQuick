package ch.hslu.mobpro.proj.thinkquick.game;

import static ch.hslu.mobpro.proj.thinkquick.game.Gesture.PAPER;
import static ch.hslu.mobpro.proj.thinkquick.game.Gesture.ROCK;
import static ch.hslu.mobpro.proj.thinkquick.game.Gesture.SCISSOR;

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
        if (winner == null) {
            isDraw = true;
        } else {
            isDraw = false;
        }
    }

    private void checkWhoWon(Gesture leftHand, Gesture rightHand) {
        winner = null;

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
    }
}