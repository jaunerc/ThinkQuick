package ch.hslu.mobpro.proj.thinkquick.activities;

import android.content.Context;
import android.content.Intent;
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

import ch.hslu.mobpro.proj.thinkquick.R;
import ch.hslu.mobpro.proj.thinkquick.game.RPSGame;
import ch.hslu.mobpro.proj.thinkquick.game.checker.ExerciseResult;
import ch.hslu.mobpro.proj.thinkquick.game.enumerations.Gesture;
import ch.hslu.mobpro.proj.thinkquick.game.enumerations.UserAnswer;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.GameSituation;
import ch.hslu.mobpro.proj.thinkquick.game.exercises.Quest;
import ch.hslu.mobpro.proj.thinkquick.game.helper.GameAnimator;
import ch.hslu.mobpro.proj.thinkquick.game.mode.EndlessMode;
import ch.hslu.mobpro.proj.thinkquick.game.mode.GameConfigGenerator;
import ch.hslu.mobpro.proj.thinkquick.game.mode.GameModeStrategy;
import ch.hslu.mobpro.proj.thinkquick.game.mode.HardcoreMode;
import ch.hslu.mobpro.proj.thinkquick.game.mode.SurpriseMode;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.Tutorial;
import ch.hslu.mobpro.proj.thinkquick.game.tutorial.TutorialFactory;
import ch.hslu.mobpro.proj.thinkquick.preferences.PreferenceSingleton;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {
    public final static String GAME_MODE_EXTRA = "gamemode";

    private final static int SKIP_POINTS = -100;
    private final static int ANIMATION_DELAY_FIRST = 500;
    private final static int ANIMATION_DELAY_SECOND = 1500;
    private Intent countdownActivity;
    private TutorialFactory tutorialFactory;
    private GameSituation currentGameSituation;
    private Context gameContext;
    private Quest currentQuest;
    private RPSGame rpsGame;
    private ImageButton rock, paper, scissor;
    private Button skip;
    private int gameModeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        countdownActivity = new Intent(this, CountdownActivity.class);
        gameContext = this;

        checkForGameMode();
        isProgressPaused(false);
        initializeUserControls();
        checkUserNeedTutorial();

        PreferenceSingleton.getHandler(this).setOnBackPressed(false);
    }

    private void checkForGameMode() {
        final Intent intent = getIntent();
        final int index = intent.getIntExtra(GameModeActivity.GAMEMODE_INDEX, GameModeActivity.NONE_MODE_INDEX);
        if(index != GameModeActivity.NONE_MODE_INDEX) {
            gameModeIndex = index;
            countdownActivity.putExtra(GAME_MODE_EXTRA, gameModeIndex);
        }
    }

    private GameModeStrategy getModeFromIndex() {
        final GameConfigGenerator generator = new GameConfigGenerator(this);
        GameModeStrategy strategy = null;
        if(gameModeIndex == GameModeActivity.ENDLESS_MODE_INDEX) {
            strategy = new EndlessMode(generator.makeEndlessConfig());
        } else if(gameModeIndex == GameModeActivity.HARDCORE_MODE_INDEX) {
            strategy = new HardcoreMode(generator.makeHardcoreConfig());
        } else if(gameModeIndex == GameModeActivity.SURPRISE_MODE_INDEX) {
            strategy = new SurpriseMode(generator.makeSurpriseConfig());
        }
        return strategy;
    }

    private void isProgressPaused(boolean isPaused) {
        PreferenceSingleton.getHandler(gameContext).setOnPausedProgress(isPaused);
    }

    private void initializeUserControls() {
        TextView labelQuest = (TextView) findViewById(R.id.challenge);
        rock = (ImageButton) findViewById(R.id.rock);
        paper = (ImageButton) findViewById(R.id.paper);
        scissor = (ImageButton) findViewById(R.id.scissor);
        skip = (Button) findViewById(R.id.skip);

        GameAnimator.fadeInElements(labelQuest, ANIMATION_DELAY_FIRST);
        GameAnimator.fadeInElements(rock, ANIMATION_DELAY_SECOND);
        GameAnimator.fadeInElements(paper, ANIMATION_DELAY_SECOND);
        GameAnimator.fadeInElements(scissor, ANIMATION_DELAY_SECOND);
        GameAnimator.fadeInElements(skip, ANIMATION_DELAY_SECOND);
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
                PreferenceSingleton.getHandler(gameContext).setExercisePoints(SKIP_POINTS);
                PreferenceSingleton.getHandler(gameContext).setExerciseResult(UserAnswer.SKIPPED);
                rpsGame.skip();
            }
        });
    }

    private void playerAnswerWith(Gesture answer) {
        ExerciseResult playerResult = rpsGame.solveWith(answer);
        if (playerResult.isCorrect()) {
            rememberResult(UserAnswer.CORRECT, playerResult.getPoints());
            rpsGame.awardPlayerPoints(playerResult.getPoints());
        } else {
            rememberResult(UserAnswer.WRONG, 0);
            rpsGame.deductPlayerLife();
        }
    }

    private void rememberResult(UserAnswer answer, int points) {
        PreferenceSingleton.getHandler(gameContext).setExercisePoints(points);
        PreferenceSingleton.getHandler(gameContext).setExerciseResult(answer);
    }

    private void startCountDownBeforeGame() {
        startActivity(countdownActivity);
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
        rpsGame.start(this, getModeFromIndex());
    }

    private void initGame() {
        int currentProgress = PreferenceSingleton.getHandler(gameContext).getCurrentProgress();
        rpsGame = new RPSGame(currentProgress);
    }

    private void checkUserNeedTutorial() {
        if (PreferenceSingleton.getHandler(gameContext).getFirstRun()) {
            enableUi(false);
            runTutorial();
        } else {
            startGame();
            playExercise();
        }
    }

    private void enableUi(boolean state) {
        rock.setEnabled(state);
        paper.setEnabled(state);
        scissor.setEnabled(state);
        skip.setEnabled(state);
    }

    private void runTutorial() {
        tutorialFactory = new TutorialFactory();
        disableUserControls();

        PreferenceSingleton.getHandler(this).setExerciseResult(UserAnswer.DEFAULT);

        tutorialFactory.initModul();
        displayTutorial(tutorialFactory.getNext());
    }

    private void disableUserControls() {
        ImageButton rock = (ImageButton) findViewById(R.id.rock);
        ImageButton paper = (ImageButton) findViewById(R.id.paper);
        ImageButton scissor = (ImageButton) findViewById(R.id.scissor);
        Button skip = (Button) findViewById(R.id.skip);

        rock.setOnClickListener(null);
        paper.setOnClickListener(null);
        scissor.setOnClickListener(null);
        skip.setOnClickListener(null);
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
                            PreferenceSingleton.getHandler(gameContext).setFirstRun(false);
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
        if (rpsGame != null) {
            if (PreferenceSingleton.getHandler(gameContext).getOnPausedProgress()) {
                rpsGame.resume();
            }
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
        if (rpsGame != null) {
            PreferenceSingleton.getHandler(gameContext).setOnBackPressed(true);
            rpsGame.gameOver();
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (PreferenceSingleton.getHandler(gameContext).getOnBackPressed() == false) {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.timeView);
            PreferenceSingleton.getHandler(gameContext).setCurrentProgress(progressBar.getProgress());
        }

        outState.putInt(GAME_MODE_EXTRA, gameModeIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        gameModeIndex = savedInstanceState.getInt(GAME_MODE_EXTRA);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public int getGameModeIndex() {
        return gameModeIndex;
    }

    public Intent getCountdownActivity() {
        return countdownActivity;
    }
}