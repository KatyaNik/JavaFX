package model;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MemoSelect {
    public Deque<Memento> mementoList = new ArrayDeque<Memento>();
    public void push(Memento state){
        mementoList.add(state);
    }
    public Memento poll(){
        return mementoList.pollLast();
    }
}
