package com.petrsu.attt.view;

import android.graphics.Bitmap;

import com.petrsu.attt.framework.Graphics;
import com.petrsu.attt.framework.Pixmap;
import com.petrsu.attt.framework.impl.AndroidGraphics;
import com.petrsu.attt.framework.impl.AndroidPixmap;

/**
 * Created by lexer on 7/31/13.
 */
public class FieldView {
    private static final int ROW_NUMBER = 3;
    private static final int COLUM_NUMBER = 3;
    private static final float BLINK_TIME = 0.5f;
    private static final int BLINK_NUMBER = 7;

    private int width;
    private int height;
    private int smallFieldSize;
    private int[] cellsX = {0, 189, 384, 0, 189, 384, 0, 189, 384};
    private int[] cellsY = {1, 1, 1, 193, 193, 193, 388, 388, 388};
    private int[] cellWidth = {203, 206, 206, 203, 206, 206, 203, 206, 206};
    private int[] cellHeight = {202, 202, 202, 205, 205, 205, 200, 200, 200};
    private AndroidGraphics g;
    Bitmap bitmap;
    int activeField = 0;
    int touchedField = 0;

    float currentTime = 0;
    boolean isActiveFieldShown = true;
    boolean isBlinked = false;
    private int currentNumberOfBlinks = 0;

    public FieldView(int width, int height) {
        this.width = width;
        this.height = height;
        this.smallFieldSize = width / ROW_NUMBER;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        g = new AndroidGraphics(null, bitmap, 1.0f);
    }

    public void update(float deltaTime) {
        currentTime += deltaTime;
        if (isBlinked) {
            if (currentNumberOfBlinks > BLINK_NUMBER) {
                isBlinked = false;
                currentNumberOfBlinks = 0;
            }
            if (currentTime > BLINK_TIME) {
                currentNumberOfBlinks++;
                isActiveFieldShown = !isActiveFieldShown;
                currentTime -= BLINK_TIME;
            }
        }
    }

    public Pixmap getPixmap() {
        draw();
        return new AndroidPixmap(bitmap, Graphics.PixmapFormat.ARGB8888);
    }

    private void draw() {
        g.drawPixmap(Assets.field, 0, 0);
        if (isActiveFieldShown) {
            g.drawPixmap(Assets.activeField, cellsX[activeField], cellsY[activeField],
                    cellsX[activeField], cellsY[activeField], cellWidth[activeField], cellHeight[activeField]);
        }
    }

    public void onClick(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        touchedField = ROW_NUMBER * (y / smallFieldSize) + x / smallFieldSize;
        if (touchedField == activeField && !isBlinked) {
            isBlinked = !isBlinked;
            currentTime = 0;
        }
        if (touchedField != activeField && !isBlinked) {
            activeField = touchedField;
        }
    }
}