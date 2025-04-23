package ru.nsu.ccfit.kupzov.lab3.gameview;

import ru.nsu.ccfit.kupzov.lab3.controller.TetrisController;
import ru.nsu.ccfit.kupzov.lab3.model.Field;

import java.util.Observable;
import ru.nsu.ccfit.kupzov.lab3.observer.Observer;
import java.awt.*;

public class FieldView extends GameView implements Observer<Void>{
    private final TetrisController tetrisController;
    private final boolean isPreview;
    
    public FieldView(TetrisController tetrisController, boolean isPriv) {
        super(tetrisController.getFieldByController(isPriv).getWidth(), tetrisController.getFieldByController(isPriv).getHigth());
        this.tetrisController = tetrisController;
        this.isPreview = isPriv;
        this.tetrisController.getFieldByController(isPriv).addObserver(this);
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
    @Override
        public void update(Void context) {
            repaint();
        }

}
