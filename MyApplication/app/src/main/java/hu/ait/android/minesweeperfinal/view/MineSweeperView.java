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

import hu.ait.android.minesweeperfinal.MainActivity;
import hu.ait.android.minesweeperfinal.R;
import hu.ait.android.minesweeperfinal.model.MineSweeperCell;
import hu.ait.android.minesweeperfinal.model.MineSweeperGrid;

public class MineSweeperView extends View {

    private Paint paintBackground;
    private Paint paintLine;
    private Paint paint;
    private List<PointF> listPoints =
            new ArrayList<PointF>();
    private Bitmap backGround;
    private boolean isOver;
    private int tX;
    private int tY;
    private boolean isFlagMode;
    private int winCount;

    public MineSweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);
        paintLine = new Paint();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);
        isFlagMode = false;
        isOver = false;
        winCount = 3;
        MineSweeperGrid.getInstance().placeMines();
        MineSweeperGrid.getInstance().placeNumbers();

    }

    public void setFlagMode(boolean bool) {
        isFlagMode = bool;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //drawing game grid
        canvas.drawRect(0, 0,
                getWidth(), getHeight(),
                paintBackground);

        drawGameArea(canvas);
        drawNumbers(canvas);
        drawFlags(canvas);
        if (isOver) {
            revealMines(canvas);
        }

    }

    public void drawGameArea(Canvas canvas) {

// four horizontal lines
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5,
                paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(),
                2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(),
                3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(),
                4 * getHeight() / 5, paintLine);

        // four vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5,
                getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5,
                getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5,
                getHeight(), paintLine);
    }

    //reveal all mines
    private void revealMines(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MineSweeperGrid.getInstance().getFieldContent(i, j).bombStatus() == true) {
                    canvas.drawText(getContext().getString(R.string.bomb), i * getWidth() / 5, (j + 1) * getHeight() / 5, paint);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        tX = ((int) event.getX()) / (getWidth() / 5);
        tY = ((int) event.getY()) / (getHeight() / 5);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFlagMode) {
                if (tX < 5 && tY < 5 && MineSweeperGrid.getInstance().getFieldContent(tX, tY).flagStatus() == false) {
                    MineSweeperCell newCell = new MineSweeperCell(MineSweeperGrid.getInstance().getFieldContent(tX, tY).adjacentNum(), true, false, false, MineSweeperGrid.getInstance().getFieldContent(tX, tY).bombStatus());
                    MineSweeperGrid.getInstance().setFieldContent(tX, tY, newCell);
                    if (tX < 5 && tY < 5 && MineSweeperGrid.getInstance().getFieldContent(tX, tY).bombStatus() == true) {
                        winCount--;
                        if (winCount == 0) {
                           winGame();
                            winCount = 3;
                        }
                    }
                    invalidate();
                }

            } else {
                if (MineSweeperGrid.getInstance().getFieldContent(tX, tY).coveredStatus() == true) {
                    if (tX < 5 && tY < 5 && MineSweeperGrid.getInstance().getFieldContent(tX, tY).bombStatus() == true) {
                        MineSweeperCell newCell = new MineSweeperCell(MineSweeperGrid.getInstance().getFieldContent(tX, tY).adjacentNum(), false, false, false, true);
                        MineSweeperGrid.getInstance().setFieldContent(tX, tY, newCell);
                        isOver = true;
                        gameOver();
                        invalidate();
                    } else if (tX < 5 && tY < 5 && MineSweeperGrid.getInstance().getFieldContent(tX, tY).adjacentStatus() == true) {
                        MineSweeperCell updateCell = new MineSweeperCell(MineSweeperGrid.getInstance().getFieldContent(tX, tY).adjacentNum(), false, true, false, false);
                        MineSweeperGrid.getInstance().setFieldContent(tX, tY, updateCell);
                        invalidate();
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void drawFlags(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MineSweeperGrid.getInstance().getFieldContent(i, j).flagStatus() == true && MineSweeperGrid.getInstance().getFieldContent(i, j).coveredStatus() == false) {
                    canvas.drawText(getContext().getString(R.string.flag), i * getWidth() / 5, (j + 1) * getHeight() / 5, paint);
                }
            }
        }
    }


    private void drawNumbers(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MineSweeperGrid.getInstance().getFieldContent(i, j).adjacentStatus() == true && MineSweeperGrid.getInstance().getFieldContent(i, j).coveredStatus() == false) {
                    canvas.drawText(Integer.toString(MineSweeperGrid.getInstance().getFieldContent(i, j).adjacentNum()), i * getWidth() / 5, (j + 1) * getHeight() / 5, paint);
                }
            }
        }
    }

    public void clearGameField() {
        listPoints.clear();
        MineSweeperGrid.getInstance().resetGrid();
        isOver = false;
        invalidate();
        MineSweeperGrid.getInstance().placeMines();
        MineSweeperGrid.getInstance().placeNumbers();
    }

    public void winGame() {
        ((MainActivity) getContext()).showSnacbarMessage(getContext().getString(R.string.win_statement));
    }

    public void gameOver() {
        ((MainActivity) getContext()).showSnacbarMessage(getContext().getString(R.string.game_over));
    }
}


