package com.petrsu.attt.model;


import com.petrsu.attt.view.LoadingScreen;
import com.petrsu.attt.framework.Screen;
import com.petrsu.attt.framework.impl.AndroidGame;

/**
 * Created by lexer on 7/21/13.
 */
public class AtttGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
