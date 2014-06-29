package com.chubakur.environment;

import java.awt.*;

/**
 * Created by User on 29.06.2014.
 */
public class Ground extends Cell {
    public String getType(){
        return "ground";
    }
    public Integer getValue(){
        return null;
    }
    public void doTick(){

    }

    public Color getColor(){
        return Color.darkGray;
    }
}
