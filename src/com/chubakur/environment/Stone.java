package com.chubakur.environment;

import java.awt.*;

/**
 * Created by User on 29.06.2014.
 */
public class Stone extends Cell {

    @Override
    public String getType() {
        return "stone";
    }

    @Override
    Integer getValue() {
        return null;
    }

    @Override
    void doTick() {

    }

    @Override
    Color getColor() {
        return Color.lightGray;
    }
}
