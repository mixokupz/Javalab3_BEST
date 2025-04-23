package ru.nsu.ccfit.kupzov.lab3.gameview;

import ru.nsu.ccfit.kupzov.lab3.controller.TetrisController;
import ru.nsu.ccfit.kupzov.lab3.model.Score;

import javax.swing.*;
import java.util.Observable;
import ru.nsu.ccfit.kupzov.lab3.observer.Observer;

public class ScoreView extends JPanel implements Observer<Integer> {

    TetrisController tetrisController;
    JTextArea jTextArea;
    ScoreView(TetrisController tetrisController){
        this.tetrisController = tetrisController;
        this.tetrisController.getScoreByController().addObserver(this);
        jTextArea = new JTextArea(tetrisController.getScoreByController().get().toString());
        add(jTextArea);
    }
    /*
    @Override
    public void update(Observable o, Object arg) {
        jTextArea.setText(this.tetrisController.getScoreByController().get().toString());
    }*/

    @Override
    public void update(Integer context) {
        jTextArea.setText(context.toString());
    }
}
