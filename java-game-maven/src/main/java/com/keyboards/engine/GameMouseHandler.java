package com.keyboards.engine;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface GameMouseHandler extends MouseListener, MouseMotionListener {

    /**
     * get the x position of the mouse
     */
    public int getX();

    /**
     * get the y position of the mouse
     */
    public int getY();

    /**
     * get the x position of the mouse
     */
    public int getButton();
}
