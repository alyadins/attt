package com.petrsu.attt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lexer on 7/30/13.
 */
public class World {
    static final int SIZE = 3;
    enum Player {
        LEFT,
        RIGHT
    }

    private List<Field> fields;
    public boolean gameOver = false;
    public int leftScore = 0;
    public int rightScore = 0;
    public Player currentPlayer;
    public Field currentField = null;


    public World() {
        //init field
        fields = new ArrayList<Field>(SIZE * SIZE);
        for (int i = 0; i < SIZE * SIZE; i++) {
            fields.add(new Field(i / SIZE, i % SIZE));
        }

        //init player
        currentPlayer = Player.LEFT;
    }


    public Field getField(int i, int j) {
        return fields.get(i * SIZE + j);
    }

    public void nextTurn() {
        if (checkOver()) {
            gameOver = true;
            return;
        }
        changePlayer();
    }

    private void changePlayer() {
        if (currentPlayer == Player.LEFT) {
            currentPlayer = Player.RIGHT;
        } else {
            currentPlayer = Player.LEFT;
        }
    }

    private boolean checkOver() {
        return false;
    }
}
