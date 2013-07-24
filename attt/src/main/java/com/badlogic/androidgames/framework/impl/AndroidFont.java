package com.badlogic.androidgames.framework.impl;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import com.badlogic.androidgames.framework.Font;

/**
 * Created by lexer on 7/24/13.
 */
public class AndroidFont implements Font{
    AssetManager assets;

    public AndroidFont(AssetManager assetManager) {
        this.assets = assetManager;
    }

    @Override
    public Typeface newFont(String path) {
        return Typeface.createFromAsset(assets, path);
    }
}
