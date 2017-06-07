package ch.hslu.mobpro.proj.thinkquick.preferences;

import android.content.Context;

/**
 * Created by Alan Meile on 05.06.2017.
 */

public class PreferenceSingleton {
    private static PreferenceHandler instance = null;

    private PreferenceSingleton() {
    }

    public static PreferenceHandler getHandler(Context context) {
        if (instance == null) {
            instance = new PreferenceHandler(context);
        }

        return instance;
    }
}
