package ch.hslu.mobpro.proj.thinkquick.game.handler;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import ch.hslu.mobpro.proj.thinkquick.R;

/**
 * Created by Alan Meile on 08.06.2017.
 */

public class CountDownBackgroundHandler {
    private Context context;
    private View countDownBackground;
    private TextView countDownPoints;

    public CountDownBackgroundHandler(Activity context) {
        this.countDownPoints = (TextView) context.findViewById(R.id.countDownPoints);
        this.countDownBackground = context.findViewById(R.id.countDownBackground);
        this.context = context;
    }

    public void setCountDownPoints(int points) {
        if (points >= 0) {
            countDownPoints.setText("+ " + Integer.toString(points));
        } else {
            countDownPoints.setText("- " + Integer.toString(-1 * points));
        }
    }

    public void setCountDownBackgroundFromAnswer(int answer) {
        switch (answer) {
            case 0:
                // AFTER TUTORIAL MODE
                break;
            case 1:
                countDownBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
                break;
            case 2:
                countDownBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
                break;
            case 3:
                countDownBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            default:
                // DO NOTHING
                break;
        }
    }
}