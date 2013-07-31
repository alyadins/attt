package com.petrsu.attt;

/**
 * Created by lexer on 7/31/13.
 */
public class Background {
    public float scaleFactor;
    public int horizontalNumberOfCells;
    public int verticalNumberOfCells;
    public int cellSize;


    public Background(int width, int height) {
        scaleFactor = width / 720.0f;
        cellSize = (int) (65.0f * scaleFactor);
        horizontalNumberOfCells = width / cellSize;
        verticalNumberOfCells = height / cellSize;
    }
}
