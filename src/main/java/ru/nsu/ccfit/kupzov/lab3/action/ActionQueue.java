package ru.nsu.ccfit.kupzov.lab3.action;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ActionQueue<T>{
    private Queue<T> actionQueue;
    public ActionQueue(){
        this.actionQueue = new ConcurrentLinkedQueue<>();
    }
    public void addAction(T action){
        actionQueue.add(action);
    }
    public boolean isAction(){
        return !actionQueue.isEmpty();
    }
    public T getAction(){
        return actionQueue.poll();
    }
}
