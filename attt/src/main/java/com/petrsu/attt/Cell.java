package com.petrsu.attt;

/**
 * Created by lexer on 7/30/13.
 */
public class Cell {
    public boolean isFree = true;
    public World.Player occupiedPlayer = null;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
