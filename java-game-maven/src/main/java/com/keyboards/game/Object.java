package com.keyboards.game;

import com.keyboards.tile.Tile;

public abstract class Object extends Entity{

    public Object(int col, int row, Tile[][] mapTiles) {
        // TODO place the object in the given col and row
    }

    public Object(Tile[][] mapTiles) {
        placeRandomly(mapTiles);
    }

    private void placeRandomly(Tile[][] mapTiles) {
        // TODO place the object randomly in the map
    }
}
