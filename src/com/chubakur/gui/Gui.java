package com.chubakur.gui;

import com.chubakur.environment.World;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOError;
import java.io.IOException;

/**
 * Created by User on 29.06.2014.
 */
public class Gui{
    private JFrame window;
    private WorldPlot worldPlot;
    private World world;
    JMenuBar mainMenu;
    JMenu mapMenu;
    public Gui(final World world){
        this.world = world;
        window = new JFrame(String.format("Animat World   [%d, %d]", 0, 0));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize(800, 600);
        mainMenu = new JMenuBar();
        mapMenu = new JMenu("Map");
        JMenuItem saveMap = new JMenuItem("Save");
        JMenuItem generateMap = new JMenuItem("Generate");
        JMenuItem loadMap = new JMenuItem("Load");
        generateMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.generator();
                worldPlot.updateUI();
            }
        });
        saveMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser(".");
                    fileChooser.showSaveDialog(window);
                    world.save(fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(window, "Saved successful");
                }catch (IOException exception){
                    JOptionPane.showMessageDialog(window, exception.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loadMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser fileChooser = new JFileChooser(".");
                    fileChooser.showOpenDialog(window);
                    world.load(fileChooser.getSelectedFile());
                    worldPlot.updateUI();
                    JOptionPane.showMessageDialog(window, "Loaded");
                }catch (IOException exception){
                    JOptionPane.showMessageDialog(window, exception.getMessage(), "IOException", JOptionPane.ERROR_MESSAGE);
                }catch (ClassNotFoundException exception){
                    JOptionPane.showMessageDialog(window, exception.getMessage(), "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mapMenu.add(generateMap);
        mapMenu.add(saveMap);
        mapMenu.add(loadMap);
        mainMenu.add(mapMenu);
        window.setJMenuBar(mainMenu);
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