package com.petrsu.attt.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.petrsu.attt.framework.Graphics;
import com.petrsu.attt.framework.Pixmap;
import com.petrsu.attt.framework.impl.AndroidPixmap;

/**
 * Created by lexer on 7/31/13.
 */
public class ChangeScreenAnimation {
    private static final int END_ALPHA = 149;
    private static final int INCREASE_ALPHA = 1;
    private static final float TIME = 0.5f;

    private float tick = TIME / (END_ALPHA / INCREASE_ALPHA);
    private float tickTime = 0;
    private int currentAlpha;
    private Canvas canvas;
    private int width;
    private int height;
    private boolean isStart;
    Bitmap bitmap;

    public ChangeScreenAnimation(int width, int height) {
        this.width = width;
        this.height = height;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        currentAlpha = 0;
    }

    ChangeScreenAnimationEndListener listener = null;

    public void setEndListener(ChangeScreenAnimationEndListener listener) {
        this.listener = listener;
    }

    public void update(float deltaTime) {
        tickTime += deltaTime;
        while (tickTime > tick) {
            if (isStart) {
                if (currentAlpha > END_ALPHA) {
                    isStart = !isStart;
                    listener.onAnimationEnd();
                    break;
                }
                currentAlpha += INCREASE_ALPHA;
                tickTime -= tick;
            }
        }
    }

    public boolean isStart() {
        return isStart;
    }

    public void start() {
        isStart = true;
    }

    public Pixmap getPixmap() {
        draw();
        return new AndroidPixmap(bitmap, Graphics.PixmapFormat.ARGB8888);
    }

    private void draw() {
        Paint paint = new Paint();
        paint.setColor(Color.argb(currentAlpha, 0, 0, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, width - 1, height - 1, paint);
    }

    interface ChangeScreenAnimationEndListener {
        public void onAnimationEnd();
    }
}
