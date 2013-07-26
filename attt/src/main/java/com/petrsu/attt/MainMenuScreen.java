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
    private int soundY = 1134;
    private int soundWidth = 128;
    private int soundHeight = 145;
    private int logoX = 62;
    private int logoY = 55;
    private int logoWidth = 603;
    private int logoHeight = 366;
    private int buttonsX = 121;
    private int buttonsY[] = {536, 715, 895};
    private int buttonsWidth = 485;
    private int buttonsHeight = 125;
    private boolean isClicked[] = {false, false, false};
    private int color = Color.parseColor("#2e8b57");
    private boolean lock = false;
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
               if (inBounds(event, soundX, soundY, soundWidth, soundHeight)) {
                   Settings.soundEnabled = !Settings.soundEnabled;
                   if(Settings.soundEnabled) {
                       Assets.click.play(0.5f);
                   }
               }

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
            if ((event.type == TouchEvent.TOUCH_DRAGGED) || (event.type == TouchEvent.TOUCH_DOWN)) {
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

        //draw sound icon
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.soundButtons, soundX, soundY, 0, 0, soundWidth, soundHeight);
        } else {
            g.drawPixmap(Assets.soundButtons, soundX, soundY, soundWidth, 0, soundWidth, soundHeight);
        }

        g.drawPixmap(Assets.logo, logoX, logoY);

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
