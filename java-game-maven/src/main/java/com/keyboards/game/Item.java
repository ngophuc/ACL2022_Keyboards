package com.keyboards.game;

import java.awt.Graphics2D;

import com.keyboards.tile.Tile;

public abstract class Item extends Object{

	int skillIncrease = 0;
	public boolean isInInventory;

	public Item (int col, int row, Tile[][] mapTiles, boolean isInInventory) {
		super(col, row, mapTiles);
		setIsInInventory(isInInventory);
	}

	public Item(Tile[][] mapTiles, float minDistance, Player player, boolean isInInventory) {
		super(mapTiles, minDistance, player);
		setIsInInventory(isInInventory);
	}

	public void setIsInInventory(boolean isInInventory) {
        this.isInInventory = isInInventory;

        if (isInInventory) {
            this.position.x = -1;
            this.position.y = -1;
        }
    }
	
	public abstract void use(Character character);

	public abstract void draw(Graphics2D g, int x, int y);
    public abstract void draw(Graphics2D g);
}
