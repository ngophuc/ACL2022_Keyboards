package com.keyboards.game;

import java.util.ArrayList;
import java.util.List;

import com.keyboards.global.Global;
import com.keyboards.tile.Tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public abstract class Mob extends Character{
    private List<Point> path;
    
    private final Stroke pathStroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    public Mob(int col, int row, Tile[][] mapTiles) {
		super(col, row, mapTiles);
	}

    public Mob(Tile[][] mapTiles) {
		super(mapTiles);
	}

    public ArrayList<Point> getPath(Entity target) {
        // TODO find the path to the target
        System.out.println("got path");
        return new ArrayList<Point>();
    }

    public void moveTowards(Entity target) {
        // TODO move towards the target
        path = getPath(target);
        System.out.println("moving towards " + target);
    }

    private void drawPath(Graphics2D g) {
        Stroke originalStroke = g.getStroke();
        g.setColor(Color.BLUE);
        g.setStroke(pathStroke);
        for (int i = 0; i < path.size() - 1; i++) {
            Point p1 = path.get(i);
            Point p2 = path.get(i + 1);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        g.setStroke(originalStroke);
    }

    public void draw(Graphics2D g) {
        if (Global.DEBUG && path != null) {
            drawPath(g);
        }

        super.draw(g);
    }
    
}
