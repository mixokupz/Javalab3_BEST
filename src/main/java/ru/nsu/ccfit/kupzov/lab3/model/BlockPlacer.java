package ru.nsu.ccfit.kupzov.lab3.model;

import ru.nsu.ccfit.kupzov.lab3.model.block.CubeBlock;

import java.util.ArrayList;

public class BlockPlacer {
    Block block;
    Field field;
    ArrayList<Point> pointArrayList;
    int pX;
    int pY;

    public BlockPlacer(Block block, Field field, int x, int y){
        this.block = block;
        this.field = field;
        this.pX = x;
        this.pY = y;
        placeBlock(x,y);
    }
    public Block getBlock(){
        return block;
    }
    public void setBlock(Block block){
        this.block = block;
        placeBlock(this.pX,this.pY);
    }
    public void rotateLeft(){
        if(block.getClass() == CubeBlock.class){
            return;
        }
        int [][] newCoords =  new int[getBlock().getSize()][2];
        int [][] oldCoords = getBlock().getCoordsOfParts().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newCoords[i][0] = -getBlock().getCoordsOfParts()[i][1];
            newCoords[i][1] = getBlock().getCoordsOfParts()[i][0];
        }
        block.setCoordsOfParts(newCoords);
        if (isCollision(pX,pY)) {
            placeBlock(pX, pY);
        } else {
            block.setCoordsOfParts(oldCoords);
        }
    }
    public void rotateRight() {
        if (block.getClass() == CubeBlock.class) {
            return;
        }
        int[][] newCoords = new int[getBlock().getSize()][2];
        int[][] oldCoords = getBlock().getCoordsOfParts().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newCoords[i][0] = getBlock().getCoordsOfParts()[i][1];
            newCoords[i][1] = -getBlock().getCoordsOfParts()[i][0];
        }
        block.setCoordsOfParts(newCoords);
        if (isCollision(pX, pY)) {
            placeBlock(pX, pY);
        } else {
            block.setCoordsOfParts(oldCoords);
        }
    }
    public void fall(){
        while(move(0,1));
    }
    public boolean move(int dX, int dY){
        int newX = pX + dX;
        int newY = pY + dY;
        if(isCollision(newX,newY)){
            placeBlock(newX,newY);
            return true;
        }else{
            return false;
        }
    }
    private boolean isCollision(int x, int y){
        for(int[] point: block.getCoordsOfParts()){
            int checkX = x + point[0];
            int checkY = y + point[1];
            if(!field.isOkX(checkX) || !field.isOkY(checkY)){
                return false;
            }
            if (!isPointMy(checkX, checkY) && field.getPointAt(checkX, checkY).getClazzObj() != null) {
                return false;
            }
        }
        return true;
    }
    private void placeBlock(int x, int y){
        if(pointArrayList != null){
            for(Point point: pointArrayList){
                point.setClazzObj(null);
            }
            field.updatePoints(pointArrayList);
        }
        pointArrayList = new ArrayList<>();
        for(int[] point: block.getCoordsOfParts()){
            pointArrayList.add(new Point(x+ point[0], y + point[1], block.getClass()));
        }
        pX = x;
        pY = y;
        field.updatePoints(pointArrayList);
    }
    private boolean isPointMy(int x, int y){
        for(Point point: pointArrayList){
            if(point.getX() == x &&  point.getY() == y){
                return true;
            }
        }
        return false;
    }

}
