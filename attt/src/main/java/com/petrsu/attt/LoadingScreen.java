package com.petrsu.attt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;

/**
 * Created by lexer on 7/21/13.
 */
public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        //Load all Assets
        Graphics g = game.getGraphics();

        //Pixmaps
        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.ARGB4444);

        //Sounds
        Assets.click = game.getAudio().newSound("click.ogg");

        //Settings
        Settings.load(game.getFileIO());

        //Start
        game.setScreen(new MainMenuScreen);
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
