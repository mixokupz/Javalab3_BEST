package ru.nsu.ccfit.kupzov.lab3;

import ru.nsu.ccfit.kupzov.lab3.controller.TetrisController;

public class Main {
    //надо prewiev вставить в контроллер
    public static void main(String[] args) {
        TetrisController tetrisController = new TetrisController();
        tetrisController.run();
    }
}