package ch.hslu.mobpro.proj.thinkquick;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import ch.hslu.mobpro.proj.thinkquick.game.tutorial.Tutorial;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.TutorialFactory;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {
    private int TUTORIAL_COLOR;
    private SharedPreferences sharedPreferences;
    private boolean tutorialDone;
    private TutorialFactory tutorialFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TUTORIAL_COLOR = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        checkUserNeedTutorial();
    }

    private void checkUserNeedTutorial() {
        String packageName = getApplicationContext().getPackageName();
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);

        if (sharedPreferences.getBoolean("firstrun", true)) {
            runTutorial();
            sharedPreferences.edit().putBoolean("firstrun", false).commit();
        }
    }

    private void runTutorial() {
        tutorialFactory = new TutorialFactory();

        tutorialFactory.initModul();
        displayTutorial(tutorialFactory.getNext());
    }

    private void displayTutorial(Tutorial topic) {
        new MaterialTapTargetPrompt.Builder(this)
                .setBackgroundColour(TUTORIAL_COLOR)
                .setTarget(findViewById(topic.getId()))
                .setPrimaryText(getResources().getString(topic.getTopic()))
                .setSecondaryText(getResources().getString(topic.getDescription()))
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget) {}

                    @Override
                    public void onHidePromptComplete()
                    {
                        if (tutorialFactory.hasTutorials()) {
                            displayTutorial(tutorialFactory.getNext());
                        }
                    }
                }).show();
    }
}