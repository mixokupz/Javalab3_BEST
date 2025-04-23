package ru.nsu.ccfit.kupzov.lab3.controller;

import ru.nsu.ccfit.kupzov.lab3.action.ActionQueue;
import ru.nsu.ccfit.kupzov.lab3.action.TetrisAction;
import ru.nsu.ccfit.kupzov.lab3.gameview.TetrisView;
import ru.nsu.ccfit.kupzov.lab3.model.*;

import ru.nsu.ccfit.kupzov.lab3.observable.Observable;


public class TetrisController extends Observable<String> implements Runnable{

    private int width;
    private int height;
    private boolean wasEnd;
    private Field field;
    private Field preview;
    private BlockFactory factory;
    private Block currentBlock;
    private Block nextBlock;
    private BlockPlacer fieldPlacer;
    private BlockPlacer previewPlacer;
    private TetrisView view;
    private ActionQueue<TetrisAction> actionQueue;
    private Score score;


    public TetrisController() {
        width = 11;
        height = 11;
        actionQueue = new ActionQueue<>();
        wasEnd = false;
        createGame(width, height);
        view = new TetrisView(this, actionQueue);
        addObserver(view);

    }

    private void createGame(int width, int height){
        score = new Score();
        field = new Field(width, height);
        preview = new Field(5, 5);
        factory = new BlockFactory();
        currentBlock = factory.makeRandomBlock();
        nextBlock = factory.makeRandomBlock();
        fieldPlacer = new BlockPlacer(currentBlock, field, field.getWidth() / 2, 0);
        previewPlacer = new BlockPlacer(nextBlock, preview, preview.getWidth() / 2, 1);
        wasEnd = false;
    }

    private void endGame() {
        if(!wasEnd){
            Score.save(score.get());
            wasEnd = true;
            //setChanged();
            notify("END_GAME");
        }
    }
    public Score getScoreByController() {
        return score;
    }
    public Field getFieldByController(boolean isPriv) {
        if(!isPriv){
            return field;
        }
        return preview;
    }
    public TetrisView getViewByController() {
        return view;
    }




    public void moveBlockLeft() {
        fieldPlacer.move(-1, 0);
    }

    public void moveBlockRight() {
        fieldPlacer.move(1, 0);
    }

    public void fallBlock() {
        fieldPlacer.fall();
    }

    public void rotateBlockLeft() {
        fieldPlacer.rotateLeft();
    }

    public void rotateBlockRight() {
        fieldPlacer.rotateRight();
    }
    public void makeStep() {
        if (!fieldPlacer.move(0, 1)) {
            for (int i = 0; i < field.getWidth(); i++) {

                if (field.getPointAt(i, 0).getClazzObj() != null) {
                    endGame();
                    return;
                }
            }
            score.add(field.clearFilledRows());
            currentBlock = nextBlock;
            fieldPlacer = new BlockPlacer(currentBlock, field, field.getWidth() / 2, 0);
            nextBlock = factory.makeRandomBlock();
            previewPlacer.setBlock(nextBlock);
        }
    }
    @Override
    public void run() {

        //setChanged();
        notify("RUN");

        while (true) {
            if (actionQueue.isAction()) {
                TetrisAction tetrisAction = actionQueue.getAction();
                switch (tetrisAction) {
                    case NEW_GAME:

                        createGame(width, height);
                        //setChanged();
                        notify("NEW_GAME");
                        break;
                    case MOVE_LEFT:
                        moveBlockLeft();
                        break;
                    case MOVE_RIGHT:
                        moveBlockRight();
                        break;
                    case ROTATE_LEFT:
                        rotateBlockLeft();
                        break;
                    case ROTATE_RIGHT:
                        rotateBlockRight();
                        break;
                    case FALL:
                        fallBlock();
                        break;
                    case STEP_GAME:
                        makeStep();
                        break;
                    case CLOSED_GAME:
                        endGame();
                        break;
                }
            }
        }
    }
}
