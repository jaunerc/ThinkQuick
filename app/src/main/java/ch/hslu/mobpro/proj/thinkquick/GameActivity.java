package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.Tutorial;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.TutorialFactory;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {
    private Intent countdownActivity;
    private SharedPreferences sharedPreferences;
    private TutorialFactory tutorialFactory;
    private GameSituation currentGameSituation;
    private Quest currentQuest;
    private RPSGame rpsGame;
    private String KEY_COUNTER = "OnSaveInstance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countdownActivity = new Intent(this, CountdownActivity.class);

        setupSharedPreferences();
        isProgressPaused(false);
        initializeUserControls();
        checkUserNeedTutorial();
    }

    private void isProgressPaused(boolean paused) {
        sharedPreferences.edit().putBoolean("onPausedProgress", paused).commit();
    }

    private void initializeUserControls() {
        ImageButton rock = (ImageButton) findViewById(R.id.rock);
        ImageButton paper = (ImageButton) findViewById(R.id.paper);
        ImageButton scissor = (ImageButton) findViewById(R.id.scissor);
        Button skip = (Button) findViewById(R.id.skip);

        setOnClickListener(rock, paper, scissor, skip);
    }

    private void setOnClickListener(ImageButton rock, ImageButton paper, ImageButton scissor, Button skip) {
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerAnswerWith(Gesture.ROCK);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerAnswerWith(Gesture.PAPER);
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerAnswerWith(Gesture.SCISSOR);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rpsGame.skip();
            }
        });
    }

    private void playerAnswerWith(Gesture answer) {
        ExerciseResult playerResult = rpsGame.solveWith(answer);
        if (playerResult.isCorrect()) {
            rpsGame.awardPlayerPoints(playerResult.getPoints());
        } else {
            rpsGame.deductPlayerLife();
        }
    }

    private void startCountDownBeforeGame() {
        startActivity(countdownActivity);
    }

    private void setupSharedPreferences() {
        String packageName = getApplicationContext().getPackageName();
        sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE);
    }

    private void playExercise() {
        updateUserProfile();
        rpsGame.nextExercise();
        currentGameSituation = getGameSituation();
        currentQuest = getQuest();
        updateView();
    }

    private void updateUserProfile() {
        TextView pointsView = (TextView) findViewById(R.id.labelPoints);
        RatingBar lifeView = (RatingBar) findViewById(R.id.lifeView);

        pointsView.setText(Integer.toString(rpsGame.getPlayerPoints()));
        lifeView.setRating(rpsGame.getPlayerLife());
    }

    private void startGame() {
        initGame();
        handOverActivityContext();
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
        if (sharedPreferences.getBoolean("firstrun", true)) {
            runTutorial();
        } else {
            startGame();
            playExercise();
        }
    }

    private void runTutorial() {
        tutorialFactory = new TutorialFactory();

        tutorialFactory.initModul();
        displayTutorial(tutorialFactory.getNext());
    }

    private void displayTutorial(Tutorial topic) {
        new MaterialTapTargetPrompt.Builder(this)
                .setBackgroundColour(ContextCompat.getColor(this, R.color.colorPrimaryDark))
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
                        } else {
                            sharedPreferences.edit().putBoolean("firstrun", false).commit();
                            startCountDownBeforeGame();
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

    @Override
    protected void onPause() {
        super.onPause();
        if (rpsGame != null) {
            rpsGame.pause();
            isProgressPaused(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.getBoolean("onPausedProgress", false)) {
            rpsGame.resume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (rpsGame != null) {
            rpsGame.stop();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.timeView);
        outState.putInt(KEY_COUNTER, progressBar.getProgress());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        int currentProgress = savedInstanceState.getInt(KEY_COUNTER);
        rpsGame.orientationChanged(currentProgress);
        super.onRestoreInstanceState(savedInstanceState);
    }
}