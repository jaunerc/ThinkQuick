package ch.hslu.mobpro.proj.thinkquick.game.tutorial;

import java.util.LinkedList;
import java.util.List;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * Created by Alan Meile on 10.05.2017.
 */

public class TutorialFactory {
    private LinkedList<Tutorial> modul = new LinkedList<>();

    public Tutorial getNext() {
        return modul.removeFirst();
    }

    public void initModul() {
        modul.add(new Tutorial(R.id.timeView, R.string.timeView_title, R.string.timeView_description));
        modul.add(new Tutorial(R.id.lifeView, R.string.lifeView_title, R.string.lifeView_description));
        modul.add(new Tutorial(R.id.labelPoints, R.string.points_title, R.string.points_description));
        modul.add(new Tutorial(R.id.labelVs, R.string.labelVs_title, R.string.labelVs_description));
        modul.add(new Tutorial(R.id.challenge, R.string.challenge_title, R.string.challenge_description));
        modul.add(new Tutorial(R.id.scissor, R.string.scissor_title, R.string.scissor_description));
        modul.add(new Tutorial(R.id.skip, R.string.skip_title, R.string.skip_description));
    }

    public boolean hasTutorials() {
        return !modul.isEmpty();
    }
}
