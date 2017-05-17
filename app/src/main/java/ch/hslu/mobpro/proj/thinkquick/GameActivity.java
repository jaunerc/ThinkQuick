package ch.hslu.mobpro.proj.thinkquick;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageButton;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.Tutorial;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.TutorialFactory;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {
    private int TUTORIAL_COLOR;
    private SharedPreferences sharedPreferences;
    private TutorialFactory tutorialFactory;
    private GameSituation currentGameSituation;
    private Quest currentQuest;
    private RPSGame rpsGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TUTORIAL_COLOR = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        checkUserNeedTutorial();

        startGame();
    }

    private void startGame() {
        initGame();
        handOverActivityContext();
        //currentGameSituation = getGameSituation();
        //currentQuest = getQuest();
        //updateView();
    }

    private void updateView() {
        ImageButton leftHand = (ImageButton) findViewById(R.id.leftHand);
        ImageButton rightHand = (ImageButton) findViewById(R.id.rightHand);
        TextView quest = (TextView) findViewById(R.id.challenge);

        leftHand.setBackground(getDrawableId(currentGameSituation.getLeftHand()));
        rightHand.setBackground(getDrawableId(currentGameSituation.getRightHand()));
        quest.setText(currentQuest.getInfo());
    }

    private Drawable getDrawableId(Gesture currentHand) {
        Drawable drawableId = ContextCompat.getDrawable(this, R.drawable.ic_scissor);
        if (currentHand == Gesture.ROCK) {
            drawableId = ContextCompat.getDrawable(this, R.drawable.ic_rock);
        } else if (currentHand == Gesture.PAPER) {
            drawableId = ContextCompat.getDrawable(this, R.drawable.ic_paper);
        }
        return drawableId;
    }

    private void handOverActivityContext() {
        rpsGame.start(this);
    }

    private void initGame() {
        rpsGame = new RPSGame();
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

    private GameSituation getGameSituation() {
        return rpsGame.getGameSituation();
    }

    private Quest getQuest() {
        return rpsGame.getQuest();
    }
}