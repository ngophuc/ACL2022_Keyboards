package com.keyboards.game;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.keyboards.global.Global;
import com.keyboards.graphics.Sprite;
import com.keyboards.tile.Tile;

public abstract class Object extends Entity{
	public Sprite[] sprite;
    public  BufferedImage image;
	public Object(int col, int row, Tile[][] mapTiles) {
        //TODO place the object in the given col and row
   	
       	
      

   	// place the character in the middle of the tile
		position.x =col * Global.TILE_SIZE + Global.TILE_SIZE/2;//   - hitBoxCornersOffset.x;
		position.y = row * Global.TILE_SIZE + Global.TILE_SIZE/2;// - hitBoxCornersOffset.y;

   	// update the hitbox position
  // 	hitbox.x = position.x + hitBoxCornersOffset.x-nombre_pixel/4;
   //	hitbox.y = position.y + hitBoxCornersOffset.y-nombre_pixel/4;

   	// update the solidbox position
   	//solidBox.x = position.x + solidBoxCornersOffset.x-nombre_pixel/4;
   	//solidBox.y = position.y + solidBoxCornersOffset.y-nombre_pixel/4;
		 init();

   }
 public Object (Tile[][] mapTiles,float distance_min,Player p) {
       
    	placeRandomly(mapTiles,distance_min,p);
    	init();
    }

    private void placeRandomly(Tile[][] mapTiles,float distance_min, Player p) {
        // TODO place the object randomly in the map
        // TODO place the object randomly in the map
		
		int col = (int) (Math.random() * Global.COL_NUM);
		int row = (int) (Math.random() * Global.ROW_NUM);
		
		// try while the randomly chosen tile is solid or null
		while (mapTiles[row][col].isSolid()) {
			col = (int) (Math.random() * Global.COL_NUM);
			row = (int) (Math.random() * Global.ROW_NUM);
		}
		while (Math.sqrt(((p.position.x-this.position.x)*(p.position.x-this.position.x)+(p.position.y-this.position.y)*(p.position.y-this.position.y)))<distance_min) {
			col = (int) (Math.random() * Global.COL_NUM);
			row = (int) (Math.random() * Global.ROW_NUM);
		
    }
		
		// place the postions in the middle of the tile
		position.x =col * Global.TILE_SIZE + Global.TILE_SIZE/2;//   - hitBoxCornersOffset.x;
		position.y = row * Global.TILE_SIZE + Global.TILE_SIZE/2;// - hitBoxCornersOffset.y;
		
		// update the hitbox position
		//hitbox.x = position.x ;//+ hitBoxCornersOffset.x- nombre_pixel/4;
		//hitbox.y = position.y;// + hitBoxCornersOffset.y- nombre_pixel/4;
	
		// update the solidbox position
		//solidBox.x = position.x + solidBoxCornersOffset.x;
		//solidBox.y = position.y + solidBoxCornersOffset.y;
		
	}
	
	protected abstract void initHitBox();
	protected abstract void initSolidBox();
	protected abstract void initSprites();

	public void init() {
		initSprites();
		initHitBox();
		initSolidBox();
		
	}
    public int[][] getSurroundingColsRows() {
        int leftX = solidBox.x;
        int rightX = solidBox.x + solidBox.width;
        int topY = solidBox.y;
        int bottomY = solidBox.y + solidBox.height;
        
        int leftTileCol = leftX / Global.TILE_SIZE;
        int rightTileCol = rightX / Global.TILE_SIZE;
        int topTileRow = topY / Global.TILE_SIZE;
        int bottomTileRow = bottomY / Global.TILE_SIZE;

        int[][] surroundingColsRows = new int[4][2];
        surroundingColsRows[0][0] = leftTileCol;
        surroundingColsRows[0][1] = topTileRow;
        surroundingColsRows[1][0] = rightTileCol;
        surroundingColsRows[1][1] = topTileRow;
        surroundingColsRows[2][0] = leftTileCol;
        surroundingColsRows[2][1] = bottomTileRow;
        surroundingColsRows[3][0] = rightTileCol;
        surroundingColsRows[3][1] = bottomTileRow;

        return surroundingColsRows;
    }
}
