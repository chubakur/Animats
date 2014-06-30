package com.chubakur.util;

/**
 * Created by User on 30.06.2014.
 */
public class Point implements Comparable {
    public int x, y;
    public Point(){
        x = y = 0;
    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Point))
            return false;
        Point p = (Point)o;
        return x == p.x && y == p.y;
    }

    @Override
    public int compareTo(Object o) {
        Point point2 = (Point)o;
        if(x == point2.x && y == point2.y) return 0;
        return 1;
    }
}
