package com.keyboards.game;

import com.keyboards.global.Global;
import com.keyboards.tile.Tile;

public abstract class Item extends Object{
	int  augmentation_competence;
	public boolean isInInventory;
	public Item (int col, int row, Tile[][] mapTiles) {
		//TODO place the object in the given col and row

		super(col, row, mapTiles);
		}

	public Item(Tile[][] mapTiles,float distance_min,Player p) {
		//TODO place the object in the given col and row

		super(mapTiles,distance_min,p);
		
		}
	
	public abstract void use(Player player);


}
