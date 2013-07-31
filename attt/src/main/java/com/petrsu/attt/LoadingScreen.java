package com.petrsu.attt;


import com.petrsu.attt.framework.Game;
import com.petrsu.attt.framework.Graphics;
import com.petrsu.attt.framework.Screen;

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
        Assets.background = g.newPixmap("background.png", Graphics.PixmapFormat.ARGB8888);
        Assets.logo = g.newPixmap("logo.png", Graphics.PixmapFormat.ARGB8888);
        Assets.menuButtons = g.newPixmap("menu_buttons.png", Graphics.PixmapFormat.ARGB8888);
        Assets.soundSettingsButtons = g.newPixmap("sound_settings_buttons.png", Graphics.PixmapFormat.ARGB8888);
        Assets.field = g.newPixmap("field.png", Graphics.PixmapFormat.ARGB8888);

        //Sounds
        Assets.click = game.getAudio().newSound("click.ogg");

        //Settings
        Settings.load(game.getFileIO());

        //Start
        game.setScreen(new MainMenuScreen(game));
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
