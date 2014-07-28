package com.chubakur.util;

/**
 * Created by chubakur on 27.07.14.
 */
public class Pair<T, D> {
    private T key;
    private D value;
    public Pair(T t, D d){
        key = t;
        value = d;
    }
    public T getKey(){
        return key;
    }
    public D getValue(){
        return value;
    }
}
