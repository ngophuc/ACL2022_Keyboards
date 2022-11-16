package com.keyboards.pathfinding;

public interface Heuristic<T> {
   
    public double calculate(Node<T> start, Node<T> target, Node<T> current);
    
}
