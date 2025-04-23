package ru.nsu.ccfit.kupzov.lab3.menuview;

import javax.swing.*;

public class GreedingView extends JFrame {
    public GreedingView(){
        JTextArea jtextArea = new JTextArea("Hello! IT IS FIRST TETRIS!\n UP - turn right\n DOWN - turn left\nLEFT - place left\n RIGHT - place right\n");
        add(jtextArea);
        setSize(300,150);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
