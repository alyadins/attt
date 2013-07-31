package com.petrsu.attt;

import android.graphics.Color;

import com.petrsu.attt.framework.Game;
import com.petrsu.attt.framework.Graphics;
import com.petrsu.attt.framework.Input.TouchEvent;
import com.petrsu.attt.framework.Screen;

import java.util.List;

/**
 * Created by lexer on 7/21/13.
 */
public class MainMenuScreen extends Screen {

    private static final int SOUND_SETTINGS_WIDTH = 136;
    private static final int SOUND_SETTINGS_HEIGHT = 136;
    private static final int BUTTONS_WIDTH = 461;
    private static final int BUTTONS_HEIGHT = 136;


    private int soundX;
    private int soundY;
    private int logoX;
    private int logoY;
    private int settingsX;
    private int settingsY;
    private int buttonsX;
    private int buttonsY[] = new int[3];

    Background bg;

    private float scaleFactor;

    private boolean isClicked[] = {false, false, false};
    private int color = Color.parseColor("#808080");
    private boolean lock = false;

    public MainMenuScreen(Game game) {
        super(game);
        initDimentions();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        //Process touch events
        for (TouchEvent event : touchEvents) {
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
            g.drawPixmap(Assets.soundSettingsButtons, soundX, soundY, 0, 0, SOUND_SETTINGS_WIDTH, SOUND_SETTINGS_HEIGHT);
        } else {
            g.drawPixmap(Assets.soundSettingsButtons, soundX, soundY, SOUND_SETTINGS_WIDTH, 0, SOUND_SETTINGS_WIDTH, SOUND_SETTINGS_HEIGHT);
        }
        //draw settings icon
        g.drawPixmap(Assets.soundSettingsButtons, settingsX, settingsY, 0, SOUND_SETTINGS_HEIGHT, SOUND_SETTINGS_WIDTH, SOUND_SETTINGS_HEIGHT);

        //draw logo
        g.drawPixmap(Assets.logo, logoX, logoY);

        drawMenu();
    }

    private void processTouchUpAction(TouchEvent event) {
        //sound
        if (inBounds(event, soundX, soundY, (int) (SOUND_SETTINGS_WIDTH * scaleFactor), (int)(SOUND_SETTINGS_HEIGHT * scaleFactor))) {
            Settings.soundEnabled = !Settings.soundEnabled;
            if(Settings.soundEnabled) {
                Assets.click.play(0.5f);
            }
        }

        //settigns
        if (inBounds(event, settingsX, settingsY, (int)(SOUND_SETTINGS_WIDTH * scaleFactor), (int) (SOUND_SETTINGS_HEIGHT * scaleFactor))) {
            if (Settings.soundEnabled) {
                Assets.click.play(0.5f);
            }
        }
        //menu buttons
        for (int j = 0; j < 3; j++) {
            if (inBounds(event, buttonsX, buttonsY[j], (int)(BUTTONS_WIDTH * scaleFactor), (int)(BUTTONS_HEIGHT * scaleFactor)) && isClicked[j]) {
                menuAction(j);
                if (Settings.soundEnabled) {
                    Assets.click.play(0.5f);
                }
            }
            isClicked[j] = false;
            lock = false;
        }
    }

    private void processTouchDownAction(TouchEvent event) {
        //menu buttons
        for (int j = 0; j < 3; j++) {
            if (inBounds(event, buttonsX, buttonsY[j], (int)(BUTTONS_WIDTH * scaleFactor), (int)(BUTTONS_HEIGHT * scaleFactor))) {
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
            if (!inBounds(event, buttonsX, buttonsY[j], (int)(BUTTONS_WIDTH * scaleFactor), (int)(BUTTONS_HEIGHT * scaleFactor))) {
                isClicked[j] = false;
            }
        }
    }

    private void drawMenu() {
        Graphics g = game.getGraphics();
        //draw menu items
        for (int i = 0; i < 3; i++) {
            if (isClicked[i]) {
                g.drawPixmap(Assets.menuButtons, buttonsX, buttonsY[i], 0, BUTTONS_HEIGHT * i,
                        BUTTONS_WIDTH, BUTTONS_HEIGHT, Color.BLACK, color);
            } else {
                g.drawPixmap(Assets.menuButtons, buttonsX, buttonsY[i], 0, BUTTONS_HEIGHT * i,
                        BUTTONS_WIDTH, BUTTONS_HEIGHT);
            }
        }
    }

    private void menuAction(int j) {
        if (j == 0) {
            game.setScreen(new GameScreen(game));
        }
    }

    private void initDimentions() {
        Graphics g = game.getGraphics();
        scaleFactor = g.getWidth() / 720.0f;

        bg = new Background(g.getWidth(), g.getHeight());

        soundX = 0;
        soundY = settingsY = (bg.verticalNumberOfCells - 2) * bg.cellSize + 1;
        settingsX = (bg.horizontalNumberOfCells - 2) * bg.cellSize + 1;

        logoX = bg.cellSize;
        logoY = bg.cellSize;

        buttonsX = bg.cellSize * 2;
        buttonsY[0] = bg.cellSize * 6;
        buttonsY[1] = bg.cellSize * 9;
        buttonsY[2] = bg.cellSize * 12;
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
