package com.petrsu.attt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lexer on 7/29/13.
 */
public class Field {
    public static final int SIZE = 3;
    private List<Cell> cells;
    public boolean isFree = true;
    public World.Player occupiedPlayer = null;

    public Field() {
        cells = new ArrayList<Cell>(SIZE * SIZE);
        for (int i = 0; i < SIZE * SIZE; i++) {
            cells.add(new Cell());
        }
    }

    public Cell getCell(int i, int j) {
        return cells.get(i * SIZE + j);
    }
}
