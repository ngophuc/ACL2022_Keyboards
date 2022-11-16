package com.keyboards.game;

import java.awt.Graphics2D;

import com.keyboards.tile.Tile;

public class Treasure extends Object {

    public Treasure(int col, int row, Tile[][] mapTiles) {
        super(col, row, mapTiles);
    }

    public Treasure(Tile[][] mapTiles) {
        super(mapTiles);
    }

    public void draw(Graphics2D g) {
        // TODO
    }
}
