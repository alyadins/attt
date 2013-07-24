package com.petrsu.attt;

import com.badlogic.androidgames.framework.Graphics;

/**
 * Created by lexer on 7/23/13.
 */
public class Grid {
    Graphics g;
    int color;
    int delta = 25;
    int lineThick = 2;
    public Grid(Graphics g, int color) {
        this.g = g;
        this.color = color;
    }

    public void draw() {
        int currentX = delta;
        while (currentX < 320) {
            g.drawRect(currentX, 0, lineThick, g.getHeight(), color);
            currentX = currentX + delta + lineThick;
        }

        int currentY = delta;
        while (currentY < 480) {
            g.drawRect(0, currentY, g.getWidth(), lineThick, color);
            currentY = currentY + delta + lineThick;
        }

    }
}
