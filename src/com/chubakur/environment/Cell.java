package com.chubakur.environment;

import java.awt.*;

/**
 * Created by User on 29.06.2014.
 */
public abstract class Cell {
    abstract public String getType();
    abstract Integer getValue();
    abstract void doTick();
    abstract Color getColor();
    public void paintSelf(Graphics g, int x, int y, int size){
        g.setColor(getColor());
        g.fillRect(x, y, size, size);
    }
}
