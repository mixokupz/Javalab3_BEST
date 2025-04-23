package ru.nsu.ccfit.kupzov.lab3.gameview;

import ru.nsu.ccfit.kupzov.lab3.action.ActionQueue;
import ru.nsu.ccfit.kupzov.lab3.action.TetrisAction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisInputHandler extends KeyAdapter {
    ActionQueue<TetrisAction> actionQueue;
    public TetrisInputHandler(ActionQueue<TetrisAction> actionQueue){
        this.actionQueue = actionQueue;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                actionQueue.addAction(TetrisAction.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                actionQueue.addAction(TetrisAction.MOVE_RIGHT);
                break;
            case KeyEvent.VK_UP:
                actionQueue.addAction(TetrisAction.ROTATE_RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                actionQueue.addAction(TetrisAction.ROTATE_LEFT);
                break;
            case KeyEvent.VK_SPACE:
                actionQueue.addAction(TetrisAction.FALL);
                break;
        }
    }
}
