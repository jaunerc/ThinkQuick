package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.game.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.PlayerStats;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.Tutorial;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.TutorialFactory;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {
    private final static int START_POINTS = 0;
    private final static int START_LIFE = 3;
    private Intent countdownActivity;
    private SharedPreferences sharedPreferences;
    private TutorialFactory tutorialFactory;
    private GameSituation currentGameSituation;
    private Quest currentQuest;
    private RPSGame rpsGame;
    private PlayerStats playerStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countdownActivity = new Intent(this, CountdownActivity.class);

        setupSharedPreferences();
        initializeUserControls();
        checkUserNeedTutorial();
    }

    private void initializeUserControls() {
        ImageButton rock = (ImageButton) findViewById(R.id.rock);
        ImageButton paper = (ImageButton) findViewById(R.id.paper);
        ImageButton scissor = (ImageButton) findViewById(R.id.scissor);

        setOnClickListener(rock, paper, scissor);
    }

    private void setOnClickListener(ImageButton rock, ImageButton paper, ImageButton scissor) {
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
                playerAnswerWith(Gesture.ROCK);
            }
        });
    }

    private void playerAnswerWith(Gesture answer) {
        ExerciseResult playerResult = rpsGame.solveWith(answer);
        if (playerResult.isCorrect()) {
            playerStats.awardPoints(playerResult.getPoints());
        } else {
            playerStats.deductLife();
        }

        startCountDown();
    }

    private void startCountDown() {
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

        pointsView.setText(Integer.toString(playerStats.getPoints()));
        lifeView.setRating(playerStats.getLife());
    }

    private void startGame() {
        createOrUpdatePlayerStats();
        initGame();
        handOverActivityContext();
    }

    private void createOrUpdatePlayerStats() {
        playerStats = new PlayerStats(this, START_POINTS, START_LIFE);
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
            sharedPreferences.edit().putBoolean("firstrun", false).commit();
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
                            startCountDown();
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