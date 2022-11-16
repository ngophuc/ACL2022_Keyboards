package com.keyboards.game;

import com.keyboards.tile.Tile;

public abstract class Item extends Object{

    public Item(int col, int row, Tile[][] mapTiles) {
        super(col, row, mapTiles);
    }

    public Item(Tile[][] mapTiles) {
        super(mapTiles);
    }

    public abstract void use(Player player);
}
