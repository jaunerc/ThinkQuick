package ch.hslu.mobpro.proj.thinkquick.game.helper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

/**
 * Created by Alan Meile on 31.05.2017.
 */

public class GameAnimator {
    private final static int DURATION = 0;

    private GameAnimator() {
    }

    public static void fadeInElements(View view, int delay) {
        view.setEnabled(false);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        fadeIn.setDuration(DURATION);
        fadeOut.setDuration(0);

        animateDelayed(view, fadeOut, fadeIn, delay);
    }

    private static void animateDelayed(final View view, final ObjectAnimator fadeOut,
                                       final ObjectAnimator animation, int delay) {
        final AnimatorSet animationSet = new AnimatorSet();
        animationSet.play(fadeOut);
        animationSet.start();

        Handler delayedCall = new Handler();
        delayedCall.postDelayed(new Runnable() {

            @Override
            public void run() {
                animationSet.play(animation);
                animationSet.start();
                view.setEnabled(true);
            }
        }, delay);
    }
}