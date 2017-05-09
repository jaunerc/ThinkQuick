package ch.hslu.mobpro.proj.thinkquick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        new MaterialTapTargetPrompt.Builder(this)
                .setTarget(findViewById(R.id.leftHand))
                .setPrimaryText("Send your first email")
                .setSecondaryText("Tap the envelop to start composing your first email")
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                    {
                        //Do something such as storing a value so that this prompt is never shown again
                    }

                    @Override
                    public void onHidePromptComplete()
                    {

                    }
                })
                .show();
    }
}
