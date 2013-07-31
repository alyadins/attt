package com.petrsu.attt.view;

import android.graphics.Bitmap;
import android.graphics.Color;

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
    private int width;
    private int height;
    private int smallFieldSize;
    private int[] cellsX = {0, 189, 384, 0, 189, 384, 0, 189, 384};
    private int[] cellsY = {0, 0, 0, 193, 193, 193, 388, 388, 388};
    private int[] cellWidth = {203, 206, 206, 203, 206, 206, 203, 206, 206};
    private int[] cellHeight = {200, 200, 200, 205, 205, 205, 200, 200, 200};
    private AndroidGraphics g;
    Bitmap bitmap;
    int activeField = 0;

    public FieldView(int width, int height) {
        this.width = width;
        this.height = height;
        this.smallFieldSize = width / ROW_NUMBER;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        g = new AndroidGraphics(null, bitmap, 1.0f);
    }

    public Pixmap getPixmap() {
        draw();
        return new AndroidPixmap(bitmap, Graphics.PixmapFormat.ARGB8888);
    }

    private void draw() {
        g.drawPixmap(Assets.field, 0, 0);
        for (int i = 0; i < ROW_NUMBER * COLUM_NUMBER; i++) {
            if (i == activeField) {
                g.drawPixmap(Assets.field, cellsX[i], cellsY[i], cellsX[i], cellsY[i], cellWidth[i], cellHeight[i], Color.BLACK, Color.RED);
            }
        }
    }

    public void onClick(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        activeField = ROW_NUMBER * (y / smallFieldSize) + x / smallFieldSize;
    }
}
