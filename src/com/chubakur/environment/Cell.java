package com.chubakur.environment;

import com.chubakur.util.PhysicalBody;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by User on 29.06.2014.
 */
public abstract class Cell extends PhysicalBody implements Serializable {
    abstract public String getType();
    abstract Integer getValue();
    abstract void doTick();
    abstract Color getColor();
    @Override
    public String toString(){
        return getType();
    }
    public void paintSelf(Graphics g, int x, int y, int size){
        g.setColor(getColor());
        g.fillRect(x, y, size, size);
    }
    public void markSelf(Graphics g, int x, int y, int size){
        g.setColor(Color.YELLOW);
        g.drawLine(x, y, x+size, y+size);
        g.drawLine(x, y+size, x+size, y);
    }

}
