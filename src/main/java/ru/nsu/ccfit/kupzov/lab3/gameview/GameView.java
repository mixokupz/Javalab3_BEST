package ru.nsu.ccfit.kupzov.lab3.gameview;

import ru.nsu.ccfit.kupzov.lab3.model.block.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameView extends JPanel {

    int fieldHeight;
    int fieldWidth;

    static HashMap<Class<?>, Color> colorMap = new HashMap<>();
    static {
        colorMap.put(CubeBlock.class, new Color(204, 102, 102));
        colorMap.put(LBlock.class, new Color(102, 204, 102));
        colorMap.put(LineBlock.class, new Color(102, 102, 204));
        colorMap.put(JBlock.class, new Color(204, 204, 102));
        colorMap.put(SBlock.class, new Color(204, 102, 204));
        colorMap.put(TBlock.class, new Color(102, 204, 204));
        colorMap.put(ZBlock.class, new Color(218, 170, 0));
    }
    public GameView(int width, int height) {
        fieldHeight = height;
        fieldWidth = width;
    }

    int squareHeight() {
        return (int) getSize().getHeight() / fieldHeight;
    }

    int squareWidth() {
        return (int) getSize().getWidth() / fieldWidth;
    }

    private Color colorPicker(Class<?> ClazzObj) {
        return ClazzObj == null?  new Color(255, 255, 255) : colorMap.get(ClazzObj);
    }

    void drawSquare(Graphics g, int j, int i, Class<?> ClazzObj) {
        int x = j * squareWidth();
        int y = i * squareHeight();

        Color color = colorPicker(ClazzObj);

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

    }
}
