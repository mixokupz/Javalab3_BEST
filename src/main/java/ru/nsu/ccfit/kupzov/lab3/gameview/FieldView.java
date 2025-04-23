package ru.nsu.ccfit.kupzov.lab3.gameview;

import ru.nsu.ccfit.kupzov.lab3.controller.TetrisController;
import ru.nsu.ccfit.kupzov.lab3.model.Field;

import java.util.Observable;
import ru.nsu.ccfit.kupzov.lab3.observer.Observer;
import java.awt.*;

public class FieldView extends GameView{
    private final TetrisController tetrisController;
    private final boolean isPreview;
    //прокинь клнтроллер
    private class FieldObserver implements Observer<Void> {

        @Override
        public void update(Void context) {
            repaint();
        }
    }

    public FieldView(TetrisController tetrisController, boolean isPriv) {
        super(tetrisController.getFieldByController(isPriv).getWidth(), tetrisController.getFieldByController(isPriv).getHigth());
        this.tetrisController = tetrisController;
        this.isPreview = isPriv;
        this.tetrisController.getFieldByController(isPriv).addObserver(new FieldObserver());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                drawSquare(g, j, i, this.tetrisController.getFieldByController(this.isPreview).getPointAt(j, i).getClazzObj());
            }
        }
    }


}
