package com.petrsu.attt;

import com.petrsu.attt.framework.Game;
import com.petrsu.attt.framework.Graphics;
import com.petrsu.attt.framework.Input.TouchEvent;
import com.petrsu.attt.framework.Screen;

import java.util.List;

/**
 * Created by lexer on 7/31/13.
 */
public class GameScreen extends Screen {

    private static final int PAUSE_WIDTH = 136;
    private static final int PAUSE_HEIGHT = 136;

    enum GameState {
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Running;
    World world;
    private int pauseX;
    private int pauseY;
    private int fieldX;
    private int fieldY;
    private float scaleFactor;
    Background bg;

    public GameScreen(Game game) {
        super(game);
        world = new World();
        initDimentions();
    }



    @Override
    public void update(float deltaTime) {
        List<TouchEvent>events = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if (state == GameState.Running) {
            updateReady(events);
        }
        if (state == GameState.Paused) {
            updatePaused(events);
        }
        if (state == GameState.GameOver) {
            updateGameOver(events);
        }
    }

    private void updateGameOver(List<TouchEvent> events) {
    }

    private void updatePaused(List<TouchEvent> events) {

    }

    private void updateReady(List<TouchEvent> events) {
        int len = events.size();
        for (TouchEvent event : events) {
            if (event.type == TouchEvent.TOUCH_UP) {
                proccesTouchUpAction(event);
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {
                processTouchDownAction(event);
            }
            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                processTouchDraggedAction(event);
            }
        }
    }

    private void processTouchDraggedAction(TouchEvent event) {

    }

    private void processTouchDownAction(TouchEvent event) {

    }

    private void proccesTouchUpAction(TouchEvent event) {
        //settigns
        if (inBounds(event, pauseX, pauseY, (int) (PAUSE_WIDTH * scaleFactor) , (int)(PAUSE_HEIGHT * scaleFactor))) {
            game.setScreen(new MainMenuScreen(game));
            if (Settings.soundEnabled) {
                Assets.click.play(0.5f);
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        //draw settings icon
        g.drawPixmap(Assets.soundSettingsButtons, pauseX, pauseY, 0, PAUSE_HEIGHT, PAUSE_WIDTH, PAUSE_HEIGHT);
        g.drawPixmap(Assets.field, fieldX, fieldY);
    }

    private void initDimentions() {
        Graphics g = game.getGraphics();
        bg = new Background(g.getWidth(), g.getHeight());
        scaleFactor = g.getWidth() / 720.0f;

        pauseX = (bg.horizontalNumberOfCells - 2) * bg.cellSize + 1;
        pauseY = (bg.verticalNumberOfCells - 2) * bg.cellSize + 1;

        fieldX = bg.cellSize + 1;
        fieldY = bg.cellSize * 3 + 1;
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
