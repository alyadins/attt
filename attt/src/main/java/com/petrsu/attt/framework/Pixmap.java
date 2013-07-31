package com.petrsu.attt.framework;

import android.graphics.Bitmap;

import com.petrsu.attt.framework.Graphics.PixmapFormat;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public Bitmap getBitmap();

    public void dispose();
}
