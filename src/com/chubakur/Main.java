package com.chubakur;

import com.chubakur.environment.World;
import com.chubakur.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Gui gui = new Gui(new World(1000));
    }
}
