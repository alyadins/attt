package com.petrsu.attt;

import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

import java.util.List;

/**
 * Created by lexer on 7/21/13.
 */
public class MainMenuScreen extends Screen {
    private int soundX = 0;
    private int soundY = 1105;
    private int settingsX = 585;
    private int settingsY = 1105;
    private int soundSettingsWidth = 136;
    private int soundSettingHeight = 136;
    private int logoX = 65;
    private int logoY = 65;
    private int logoWidth = 591;
    private int logoHeight = 268;
    private int buttonsX = 130;
    private int buttonsY[] = {390, 585, 780};
    private int buttonsWidth = 461;
    private int buttonsHeight = 136;
    private boolean isClicked[] = {false, false, false};
    private int color = Color.parseColor("#808080");
    private boolean lock = false;
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        //Process touch events
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                processTouchUpAction(event);
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {
                processTouchDownAction(event);
            }
            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                processTouchDraggedAction(event);
            }
        }
    }


    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);

        //draw sound icon
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.soundSettingsButtons, soundX, soundY, 0, 0, soundSettingsWidth, soundSettingHeight);
        } else {
            g.drawPixmap(Assets.soundSettingsButtons, soundX, soundY, soundSettingsWidth, 0, soundSettingsWidth, soundSettingHeight);
        }

        //draw settings icon
        g.drawPixmap(Assets.soundSettingsButtons, settingsX, settingsY, 0, soundSettingHeight, soundSettingsWidth, soundSettingHeight);

        //draw logo
        g.drawPixmap(Assets.logo, logoX, logoY);

        drawMenu();
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

    private void processTouchUpAction(TouchEvent event) {
        //sound
        if (inBounds(event, soundX, soundY, soundSettingsWidth, soundSettingHeight)) {
            Settings.soundEnabled = !Settings.soundEnabled;
            if(Settings.soundEnabled) {
                Assets.click.play(0.5f);
            }
        }
        //settigns
        if (inBounds(event, settingsX, settingsY, soundSettingsWidth, soundSettingHeight)) {
            if (Settings.soundEnabled) {
                Assets.click.play(0.5f);
            }
        }

        //menu buttons
        for (int j = 0; j < 3; j++) {
            if (inBounds(event, buttonsX, buttonsY[j], buttonsWidth, buttonsHeight) && isClicked[j]) {
                if (Settings.soundEnabled) {
                    Assets.click.play(0.5f);
                }
                Log.d("TEST", String.valueOf(j));
            }
            isClicked[j] = false;
            lock = false;
        }
    }
    private void processTouchDownAction(TouchEvent event) {
        //menu buttons
        for (int j = 0; j < 3; j++) {
            if (inBounds(event, buttonsX, buttonsY[j], buttonsWidth, buttonsHeight)) {
                if (!lock) {
                    isClicked[j] = true;
                    lock = true;
                }
            } else {
                isClicked[j] = false;
            }
        }
    }

    private void processTouchDraggedAction(TouchEvent event) {
        //menu buttons
        for (int j = 0; j < 3; j++) {
            if(!inBounds(event, buttonsX, buttonsY[j], buttonsWidth, buttonsHeight)) {
                isClicked[j] = false;
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

    private void drawMenu() {
        Graphics g = game.getGraphics();
        //draw menu items
        for (int i = 0; i < 3; i++) {
            if (isClicked[i]) {
                g.drawColoredPixmap(Assets.menuButtons, buttonsX, buttonsY[i], 0, buttonsHeight * i,
                        buttonsWidth, buttonsHeight, Color.BLACK, color);
            } else {
                g.drawPixmap(Assets.menuButtons, buttonsX, buttonsY[i], 0, buttonsHeight * i,
                        buttonsWidth, buttonsHeight);
            }
        }
    }
}
