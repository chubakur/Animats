package com.chubakur.environment;

import com.chubakur.util.Point;
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
        System.out.println();

        int water_sources = 10;
        int minimal_water_source_size = 100;
        int max_water_source_size = 10000;
        int rwater_sources = 0;
        double water_inc_chance = 0.4;
        while(rwater_sources < water_sources){
            int x = random.nextInt(cells.length);
            int y = random.nextInt(cells.length);
            Queue<Pair<Integer, Integer>> subWater = new LinkedBlockingQueue<Pair<Integer, Integer>>();
            Map<Pair<Integer, Integer>, Cell> backup = new HashMap<Pair<Integer, Integer>, Cell>();
            if(!(getCell(x, y) instanceof Water)){
                int water_source_size = 1;
                backup.put(new Pair<Integer, Integer>(x,y), cells[y][x]);
                cells[y][x] = new Water();
                subWater.add(new Pair<Integer, Integer>(x, y));
                while(subWater.size() > 0 && water_source_size < max_water_source_size){
                    Pair<Integer, Integer> sc = subWater.element();
                    subWater.remove();
                    int x1 = sc.getKey();
                    int y1 = sc.getValue();
                    if(x1 > 0){
                        if(random.nextDouble() > water_inc_chance){
                            if(!(cells[y1][x1-1] instanceof Water)) {
                                backup.put(new Pair<Integer, Integer>(x1-1,y1), cells[y1][x1-1]);
                                cells[y1][x1 - 1] = new Water();
                                ++water_source_size;
                                subWater.add(new Pair<Integer, Integer>(x1 - 1, y1));
                            }
                        }
                    }
                    if(x1 < cells.length-1){
                        if(random.nextDouble() > water_inc_chance){
                            if(!(cells[y1][x1+1] instanceof Water)) {
                                backup.put(new Pair<Integer, Integer>(x1+1,y1), cells[y1][x1+1]);
                                cells[y1][x1 + 1] = new Water();
                                ++water_source_size;
                                subWater.add(new Pair<Integer, Integer>(x1 + 1, y1));
                            }
                        }
                    }
                    if(y1 > 0){
                        if(random.nextDouble() > water_inc_chance){
                            if(!(cells[y1-1][x1] instanceof Water)) {
                                backup.put(new Pair<Integer, Integer>(x1,y1-1), cells[y1-1][x1]);
                                cells[y1 - 1][x1] = new Water();
                                ++water_source_size;
                                subWater.add(new Pair<Integer, Integer>(x1, y1 - 1));
                            }
                        }
                    }
                    if(y1 < cells.length-1){
                        if(random.nextDouble() > water_inc_chance){
                            if(!(cells[y1+1][x1] instanceof Water)) {
                                backup.put(new Pair<Integer, Integer>(x1,y1+1), cells[y1+1][x1]);
                                cells[y1 + 1][x1] = new Water();
                                ++water_source_size;
                                subWater.add(new Pair<Integer, Integer>(x1, y1 + 1));
                            }
                        }
                    }
                }
                if(water_source_size < minimal_water_source_size){
                    for(Map.Entry<Pair<Integer, Integer>, Cell> entry : backup.entrySet()){
                        cells[entry.getKey().getValue()][entry.getKey().getKey()] = entry.getValue();
                    }
                }else{
                    System.out.print(String.format("Water: %d/%d\r", ++rwater_sources, water_sources));
                }
            }
        }
        System.out.println();
        int little_island_size = 10;
        for(int i=0; i<cells.length; ++i){
            for(int j=0; j<cells.length; ++j){
                Cell cell = getCell(j, i);
                if(!(cell instanceof Water)){
                    Queue<Point> points = new LinkedBlockingQueue<Point>();
                    Set<Point> pointSet = new TreeSet<Point>();
                    Point point1 = new Point(j, i);
                    points.add(point1);
                    pointSet.add(point1);
                    //System.out.println(points.contains(new Point(j, i)));
                    while(!points.isEmpty() && pointSet.size() <= little_island_size+1) {
                        Point point = points.element();
                        points.remove();
                        //слева
                        if (point.x > 0) {
                            if (!(getCell(point.x - 1, point.y) instanceof Water)){
                                Point npoint = new Point(point.x-1, point.y);
                                if(!pointSet.contains(npoint)){
                                    pointSet.add(npoint);
                                    points.add(npoint);
                                }
                            }
                        }
                        //справа
                        if (point.x < cells.length - 1) {
                            if (!(getCell(point.x + 1, point.y) instanceof Water)){
                                Point npoint = new Point(point.x+1, point.y);
                                if(!pointSet.contains(npoint)){
                                    pointSet.add(npoint);
                                    points.add(npoint);
                                }
                            }
                        }
                        //сверху
                        if (point.y > 0) {
                            if (!(getCell(point.x, point.y - 1) instanceof Water)){
                                Point npoint = new Point(point.x, point.y-1);
                                if(!pointSet.contains(npoint)){
                                    pointSet.add(npoint);
                                    points.add(npoint);
                                }
                            }
                        }
                        //снизу
                        if (point.y < cells.length - 1) {
                            if (!(getCell(point.x, point.y + 1) instanceof Water)){
                                Point npoint = new Point(point.x, point.y+1);
                                if(!pointSet.contains(npoint)){
                                    pointSet.add(npoint);
                                    points.add(npoint);
                                }
                            }
                        }
                    }
                    if(pointSet.size() <= little_island_size){
                        for(Point p : pointSet){
                            cells[p.y][p.x] = new Water();
                        }
                    }
                }
            }
            System.out.print(String.format("Remove little islands: %d/%d\r", i + 1, cells.length));
        }
        System.out.println();

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
