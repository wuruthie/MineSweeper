package hu.ait.android.minesweeperfinal.view;

/**
 * Created by ruthwu on 3/1/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hu.ait.android.minesweeperfinal.model.MineSweeperGrid;

public class MineSweeperView extends View {

    private Paint paintBackground;
    private Paint paintLine;
    private Paint paint;
    private List<PointF> listPoints =
            new ArrayList<PointF>();
    private Bitmap backGround;
    private int x;
    private int y;

    public MineSweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paint = new Paint();
        paint.setColor(Color.RED);
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //drawing game grid
        canvas.drawRect(0, 0,
                getWidth(), getHeight(),
                paintBackground);

        drawGameArea(canvas);
    }

    public void drawGameArea(Canvas canvas) {
        //vertical lines
        canvas.drawLine(dpToPx(60), 0, dpToPx(60), getHeight(), paintLine);
        canvas.drawLine(dpToPx(120), 0, dpToPx(120), getHeight(), paintLine);
        canvas.drawLine(dpToPx(180), 0, dpToPx(180), getHeight(), paintLine);
        canvas.drawLine(dpToPx(240), 0, dpToPx(240), getHeight(), paintLine);

        //horizontal lines
        canvas.drawLine(0, dpToPx(60), getWidth(), dpToPx(60), paintLine);
        canvas.drawLine(0, dpToPx(120), getWidth(), dpToPx(120), paintLine);
        canvas.drawLine(0, dpToPx(180), getWidth(), dpToPx(180), paintLine);
        canvas.drawLine(0, dpToPx(240), getWidth(), dpToPx(240), paintLine);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

}
