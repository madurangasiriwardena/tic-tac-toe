package com.example.maduranga.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DrawView extends View {

    private List<List<Square>> squares;
    private Paint paint;
    private Paint boarderPaint;
    private Player chance;
    private Paint player1;
    private Paint player2;
    private boolean touching;

    private List<Entry> rows;
    private List<Entry> columns;
    private List<Entry> diagonals;
    private int gridSize = 3;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {

        squares = new ArrayList<>();

        initPaints();

        drawGrid();

        rows = new ArrayList<>();
        columns = new ArrayList<>();
        diagonals = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            rows.add(new Entry());
            columns.add(new Entry());
        }
        for (int i = 0; i < 2; i++) {
            diagonals.add(new Entry());
        }

        chance = Player.PLAYER_1;
        touching = false;
    }

    protected void initPaints() {
        paint = new Paint();
        paint.setColor(Color.GRAY);

        player1 = new Paint();
        player1.setColor(Color.BLUE);

        player2 = new Paint();
        player2.setColor(Color.RED);

        boarderPaint = new Paint();
        boarderPaint.setStyle(Paint.Style.STROKE);
        boarderPaint.setColor(Color.BLACK);
        boarderPaint.setStrokeWidth(5);
    }

    protected void drawGrid() {
        int x = 50;
        int y = 50;
        int sideLength = (getResources().getDisplayMetrics().widthPixels - 100) / 3;

        for (int i = 0; i < 3; i++) {
            List<Square> row = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                int left = x + sideLength * j;
                int top = y + sideLength * i;
                Rect singleRectangle = new Rect(left, top, left + sideLength, top + sideLength);
                Square square = new Square(singleRectangle);
                row.add(square);
            }
            squares.add(row);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (squares.get(i).get(j).getOwner() == Player.PLAYER_1) {
                    canvas.drawRect(squares.get(i).get(j).getRect(), player1);
                } else if (squares.get(i).get(j).getOwner() == Player.PLAYER_2) {
                    canvas.drawRect(squares.get(i).get(j).getRect(), player2);
                } else {
                    canvas.drawRect(squares.get(i).get(j).getRect(), paint);
                }
                canvas.drawRect(squares.get(i).get(j).getRect(), boarderPaint);
            }
        }

        if (touching) {
            if (chance == Player.PLAYER_1) {
                chance = Player.PLAYER_2;
            } else {
                chance = Player.PLAYER_1;
            }

            for (int i = 0; i < gridSize; i++) {
                if ((rows.get(i).getPlayer_1() == gridSize && rows.get(i).getPlayer_2() == 0) || (columns.get(i).getPlayer_1() == gridSize && columns.get(i).getPlayer_2() == 0)) {
                    showWinner(Player.PLAYER_1);
                    break;
                } else if ((rows.get(i).getPlayer_2() == gridSize && rows.get(i).getPlayer_1() == 0) || (columns.get(i).getPlayer_2() == gridSize && columns.get(i).getPlayer_1() == 0)) {
                    showWinner(Player.PLAYER_2);
                    break;
                }
            }
            for (int i = 0; i < 2; i++) {
                if (diagonals.get(i).getPlayer_1() == gridSize && diagonals.get(i).getPlayer_2() == 0) {
                    showWinner(Player.PLAYER_1);
                    break;
                } else if (diagonals.get(i).getPlayer_2() == gridSize && diagonals.get(i).getPlayer_1() == 0) {
                    showWinner(Player.PLAYER_2);
                    break;
                }
            }
        }
    }

    protected void showWinner(Player player) {
        Context context = getContext();
        Intent intent = new Intent(context, WinActivity.class);
        String message = "Player \"" + player.getName() + "\" has won ";

        intent.putExtra(Constants.EXTRA_MESSAGE, message);
        context.startActivity(intent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();

        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (squares.get(i).get(j).getRect().contains(touchX, touchY)) {
                            squares.get(i).get(j).setOwner(chance);
                            touching = true;
                            if (chance == Player.PLAYER_1) {
                                rows.get(i).setPlayer_1();
                                columns.get(j).setPlayer_1();
                                if (i == j) {
                                    diagonals.get(0).setPlayer_1();
                                }
                                if (i == 2 - j) {
                                    diagonals.get(1).setPlayer_1();
                                }
                            } else {
                                rows.get(i).setPlayer_2();
                                columns.get(j).setPlayer_2();
                                if (i == j) {
                                    diagonals.get(0).setPlayer_2();
                                }
                                if (i == 2 - j) {
                                    diagonals.get(1).setPlayer_2();
                                }
                            }
                            invalidate();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                touching = false;
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    public boolean performClick() {
        return super.performClick();
    }
}
