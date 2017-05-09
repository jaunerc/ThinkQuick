package ch.hslu.mobpro.proj.thinkquick.game;

import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;

/**
 * Created by Alan Meile on 08.05.2017.
 */

public interface Exercise {
    GameSituation getGameSituation();
    Quest getQuest();
}
