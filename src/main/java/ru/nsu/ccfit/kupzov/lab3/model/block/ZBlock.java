package ru.nsu.ccfit.kupzov.lab3.model.block;

import ru.nsu.ccfit.kupzov.lab3.model.Block;

public class ZBlock implements Block {
    int[][] coords = {
            {-1, 0}, {0, 0}, {0, 1}, {1, 1}
    };

    @Override
    public int[][] getCoordsOfParts() {
        return coords;
    }

    @Override
    public void setCoordsOfParts(int[][] newCoordsOfParts) {
        this.coords = newCoordsOfParts;
    }

    @Override
    public int getSize() {
        return coords.length;
    }
}
