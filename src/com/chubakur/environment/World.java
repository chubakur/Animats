package com.chubakur.environment;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by User on 29.06.2014.
 */
public class World {
    private Cell[][] cells;
    void randGenerator(){
        boolean a = false;
        Class[] classes = {Grass.class, Ground.class};
        Random random = new Random();
        for(int i=0;i<cells.length;++i){
            for(int j=0;j<cells.length;++j){
                Class c = classes[random.nextInt(classes.length)];
                try {
                    cells[i][j] = (Cell)c.newInstance();
                }catch (InstantiationException e){
                    System.out.println(e.getMessage());
                }catch (IllegalAccessException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    void generator(){
        Random random = new Random();
        for(int i=0;i<cells.length;++i){
            for(int j=0;j<cells.length;++j){
                cells[i][j] = new Grass();
            }
        }
        System.out.println("Grass generated");
        double percent_of_ground = 10;
        int ground_pieces = (int)Math.ceil(cells.length*cells.length/100*percent_of_ground);
        while(ground_pieces > 0){
            int x = random.nextInt(cells.length);
            int y = random.nextInt(cells.length);
            if(!(getCell(x, y) instanceof Ground)) {
                cells[y][x] = new Ground();
                --ground_pieces;
            }
        }
        System.out.println("Ground generated");

        int stones = 150;
        int rstones = 0;
        int max_stone_size = 10000;
        while(rstones < stones){
            int x = random.nextInt(cells.length);
            int y = random.nextInt(cells.length);
            Queue<Pair<Integer, Integer>> subStones = new LinkedBlockingQueue<Pair<Integer, Integer>>();
            if(!(getCell(x, y) instanceof Stone)) {
                rstones++;
                System.out.print(String.format("Stones: %d/%d\r", rstones, stones));
                cells[y][x] = new Stone();
                subStones.add(new Pair<Integer, Integer>(x, y));
                int stone_size = 1;
                double p = 0.55;
                while(subStones.size() > 0 && stone_size < max_stone_size){
                    Pair<Integer, Integer> sc = subStones.element();
                    subStones.remove();
                    int x1 = sc.getKey();
                    int y1 = sc.getValue();
                    if(x1 > 0){
                        if(random.nextDouble() > p){
                            if(!(cells[y1][x1-1] instanceof Stone)) {
                                cells[y1][x1 - 1] = new Stone();
                                ++stone_size;
                                subStones.add(new Pair<Integer, Integer>(x1 - 1, y1));
                            }
                        }
                    }
                    if(x1 < cells.length-1){
                        if(random.nextDouble() > p){
                            if(!(cells[y1][x1+1] instanceof Stone)) {
                                cells[y1][x1 + 1] = new Stone();
                                ++stone_size;
                                subStones.add(new Pair<Integer, Integer>(x1 + 1, y1));
                            }
                        }
                    }
                    if(y1 > 0){
                        if(random.nextDouble() > p){
                            if(!(cells[y1-1][x1] instanceof Stone)) {
                                cells[y1 - 1][x1] = new Stone();
                                ++stone_size;
                                subStones.add(new Pair<Integer, Integer>(x1, y1 - 1));
                            }
                        }
                    }
                    if(y1 < cells.length-1){
                        if(random.nextDouble() > p){
                            if(!(cells[y1+1][x1] instanceof Stone)) {
                                cells[y1 + 1][x1] = new Stone();
                                ++stone_size;
                                subStones.add(new Pair<Integer, Integer>(x1, y1 + 1));
                            }
                        }
                    }
                }
            }

        }

        return;
    }
    public World(int size){
        cells = new Cell[size][size];
        generator();
    }
    public int getSize(){
        return cells.length;
    }
    public Cell getCell(int x, int y){
        return cells[y][x];
    }
}
