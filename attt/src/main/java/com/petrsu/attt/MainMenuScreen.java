package com.petrsu.attt;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by lexer on 7/21/13.
 */
public class MainMenuScreen extends Screen {
    private static int SOUND_X = 0;
    private static int SOUND_Y = 1134;
    private static int SOUND_WIDTH = 128;
    private static int SOUND_HEIGHT = 145;
    private static int LOGO_X = 62;
    private static int LOGO_Y = 55;
    private static int LOGO_WIDTH = 603;
    private static int LOGO_HEIGHT = 366;
    private static int BUTTONS_X = 121;
    private static int BUTTON1_Y = 536;
    private static int BUTTON2_Y = 715;
    private static int BUTTON3_Y = 895;
    private static int BUTTONS_WIDTH = 485;
    private static int BUTTONS_HEIGHT = 125;
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();

        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
               if (inBounds(event, SOUND_X, SOUND_Y, SOUND_WIDTH, SOUND_HEIGHT)) {
                   Settings.soundEnabled = !Settings.soundEnabled;
                   if(Settings.soundEnabled) {
                       Assets.click.play(0.5f);
                   }
               }
               if (inBounds(event, BUTTONS_X, BUTTON1_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT)) {
                   if (Settings.soundEnabled) {
                       Assets.click.play(0.5f);
                   }
               }
               if (inBounds(event, BUTTONS_X, BUTTON2_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT)) {
                   if (Settings.soundEnabled) {
                       Assets.click.play(0.5f);
                   }
               }
               if (inBounds(event, BUTTONS_X, BUTTON3_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT)) {
                   if (Settings.soundEnabled) {
                       Assets.click.play(0.5f);
                   }
               }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.soundButtons, SOUND_X, SOUND_Y, 0, 0, SOUND_WIDTH, SOUND_HEIGHT);
        } else {
            g.drawPixmap(Assets.soundButtons, SOUND_X, SOUND_Y, SOUND_WIDTH, 0, SOUND_WIDTH, SOUND_HEIGHT);
        }

        g.drawPixmap(Assets.logo, LOGO_X, LOGO_Y);
        g.drawPixmap(Assets.menuButtons, BUTTONS_X, BUTTON1_Y, 0, 0, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        g.drawPixmap(Assets.menuButtons, BUTTONS_X, BUTTON2_Y, 0, BUTTONS_HEIGHT, BUTTONS_WIDTH, BUTTONS_HEIGHT);
        g.drawPixmap(Assets.menuButtons, BUTTONS_X, BUTTON3_Y, 0, BUTTONS_HEIGHT * 2, BUTTONS_WIDTH, BUTTONS_HEIGHT);
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
