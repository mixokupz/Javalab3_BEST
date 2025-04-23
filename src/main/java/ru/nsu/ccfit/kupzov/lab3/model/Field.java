package ru.nsu.ccfit.kupzov.lab3.model;

import java.util.List;
import ru.nsu.ccfit.kupzov.lab3.observable.Observable;

public class Field extends Observable<Void> {
    private int width;
    private int higth;

    private Point[][] field;

    public Field(int w,int h){
        this.width = w;
        this.higth = h;
        this.field = new Point[this.width][this.higth];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.higth; j++) {
                field[i][j] = new Point(i, j);
            }
        }
    }
    public Point getPointAt(int x, int y){
        return field[x][y];
    }
    public int getWidth(){
        return width;
    }
    public int getHigth(){
        return higth;
    }
    public boolean isOkX(int x){
        return (x >= 0 && x < width);
    }
    public boolean isOkY(int y){
        return (y >= 0 && y < width);
    }
    public void updatePoints(List<Point> pointsList) {
        boolean isGood = true;
        for (Point point : pointsList) {
            if (!isOkX(point.getX()) || !isOkY(point.getY())) {
                isGood = false;
                break;
            }
        }
        if(isGood){
            for (Point point : pointsList) {
                field[point.getX()][point.getY()] = point;
            }
            //setChanged();
            notify(null);
        }
    }
    public int clearFilledRows() {
        int count = 0;
        for (int y = 0; y < higth; y++) {
            int filledPoints = 0;
            for (int x = 0; x < width; x++) {
                if (field[x][y].getClazzObj() != null) {
                    filledPoints += 1;
                }
            }
            if (filledPoints == width) {
                count += 1;
                for (int t = y - 1; t >= 0; t--) {
                    for (int x = 0; x < width; x++) {
                        if (t == 0) {
                            field[x][t].setClazzObj(null);
                        } else {
                            field[x][t+1].setClazzObj(field[x][t].getClazzObj());
                        }
                    }
                }
            }
        }
        if (count > 0) {
            //setChanged();
            notify(null);
        }
        return count;
    }


}
