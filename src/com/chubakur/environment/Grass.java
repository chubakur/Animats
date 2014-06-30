package com.chubakur.environment;

import java.awt.*;
import java.util.Random;

/**
 * Created by User on 29.06.2014.
 */
public class Grass extends Cell {
    double value;

    public Grass(){
        Random random = new Random();
        double rangeMin = 0, rangeMax = 100;
        value = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }

    public Grass(int value){
        this.value = value;
    }

    public String getType(){
        return "grass";
    }

    @Override
    public String toString(){
        return String.format("Grass %d%%", getValue());
    }

    public Integer getValue(){
        return (int)Math.floor(value);
    }

    public void doTick(){

    }

    public Color getColor(){
        return new Color((float)0.0,(float)((255.0/100.0*value)/255),(float)0.0);
    }
}
