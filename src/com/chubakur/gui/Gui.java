package com.chubakur.gui;

import com.chubakur.environment.World;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by User on 29.06.2014.
 */
public class Gui{
    private JFrame window;
    private WorldPlot worldPlot;
    private World world;
    public Gui(final World world){
        this.world = world;
        window = new JFrame(String.format("Animat World   [%d, %d]", 0, 0));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize(800, 600);
        worldPlot = new WorldPlot(world);
        window.add(worldPlot);
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT: worldPlot.moveRight(); break;
                    case KeyEvent.VK_LEFT : worldPlot.moveLeft() ; break;
                    case KeyEvent.VK_UP   : worldPlot.moveUp()   ; break;
                    case KeyEvent.VK_DOWN : worldPlot.moveDown() ; break;
                    case KeyEvent.VK_NUMPAD9 : worldPlot.incBlockSize(); break;
                    case KeyEvent.VK_NUMPAD7 : worldPlot.decBlockSize(); break;
                    default: return;
                }
                window.setTitle(String.format("Project \"Animal\"   [%d, %d] [%d, %d] ScaleFactor: %d", worldPlot.x, worldPlot.y, worldPlot.x + worldPlot.count_x, worldPlot.y + worldPlot.count_y, worldPlot.getBlockSize()));
                worldPlot.updateUI();
            }
        });
    }
}