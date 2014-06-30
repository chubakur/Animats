package com.chubakur.gui;

import com.chubakur.environment.Cell;
import com.chubakur.environment.World;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 29.06.2014.
 */
public class WorldPlot extends JPanel {
    private World world;
    int count_x, count_y;
    int x, y;
    private int block_size = 10;
    WorldPlot(World world){
        this.world = world;
        x = y = 0;
    }
    public int getBlockSize(){
        return block_size;
    }
    public void incBlockSize(){
        ++block_size;
    }
    public void decBlockSize(){
        if(block_size > 1)
            --block_size;
    }
    @Override
    public void paintComponent(Graphics g){
        Dimension size = this.getSize();
        count_x = size.width/block_size;
        count_y = size.height/block_size;
        super.paintComponent(g);
        for(int i=0;i<count_y;++i){
            for(int j =0;j<count_x;++j){
                try {
                    Cell cell = world.getCell(x + j, y + i);
                    if(cell != null)
                        cell.paintSelf(g, j*block_size, i*block_size, block_size);
                }catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
    }
    public void moveLeft(){
        if(x > 0) --x;
    }
    public void moveRight(){
        if(x + count_x < world.getSize()) ++x;
    }
    public void moveUp(){
        if(y > 0) --y;
    }
    public void moveDown(){
        if(y + count_y < world.getSize()) ++y;
    }
}