package com.keyboards.game;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

import com.keyboards.global.Global;
import com.keyboards.tile.Tile;

public abstract class Character extends Entity{
	public int health;
	public int attackDamage;
	public int speed;
	int sprintSpeed;
	public boolean isSprinting = false;

	public Tile[][] mapTiles;

	public final int RIGHT = 0;
    public final int LEFT = 1;
    public final int IDLE = 2;

	public int lastDirection = RIGHT;
	public int direction = IDLE;

	public Character(int col, int row, Tile[][] mapTiles) {
		this.mapTiles = mapTiles;
		
		if (col < 0 || col >= mapTiles.length || row < 0 || row >= mapTiles[0].length) {
			throw new IllegalArgumentException("The character is out of the map");
		}
		
		if (mapTiles[row][col].isSolid()) {
			placeRandomly(mapTiles);
			System.out.println("The chosen tile is solid, placing the character randomly");
		}
		
		init();

		// place the character hitbox in the middle of the tile
		position.x = col * Global.TILE_SIZE + Global.TILE_SIZE / 2 - hitbox.width / 2 - hitBoxCornersOffset.x;
		position.y = row * Global.TILE_SIZE + Global.TILE_SIZE / 2 - hitbox.height / 2 - hitBoxCornersOffset.y;

		// update the hitbox position
		hitbox.x = position.x + hitBoxCornersOffset.x;
		hitbox.y = position.y + hitBoxCornersOffset.y;

		// update the solidbox position
		solidBox.x = position.x + solidBoxCornersOffset.x;
		solidBox.y = position.y + solidBoxCornersOffset.y;
	}

	public Character(Tile[][] mapTiles) {
		this.mapTiles = mapTiles;

		init();

		placeRandomly(mapTiles);
	}

	private void placeRandomly(Tile[][] mapTiles) {
		int col = (int) (Math.random() * Global.COL_NUM);
		int row = (int) (Math.random() * Global.ROW_NUM);
		
		// try while the randomly chosen tile is solid or null
		while (mapTiles[row][col].isSolid()) {
			col = (int) (Math.random() * Global.COL_NUM);
			row = (int) (Math.random() * Global.ROW_NUM);
		}

		// place the character in the middle of the tile
		position.x = col * Global.TILE_SIZE + Global.TILE_SIZE / 2 - hitbox.width / 2 - hitBoxCornersOffset.x;
		position.y = row * Global.TILE_SIZE + Global.TILE_SIZE / 2 - hitbox.height / 2 - hitBoxCornersOffset.y;

		// update the hitbox position
		hitbox.x = position.x + hitBoxCornersOffset.x;
		hitbox.y = position.y + hitBoxCornersOffset.y;

		// update the solidbox position
		solidBox.x = position.x + solidBoxCornersOffset.x;
		solidBox.y = position.y + solidBoxCornersOffset.y;

		System.out.println("Placed " + this + " at " + col + ", " + row);
	}

	protected abstract void initStats();
	protected abstract void initHitBox();
	protected abstract void initSolidBox();
	protected abstract void initSprites();

	private void init() {
		initStats();
		initHitBox();
		initSolidBox();
		initSprites();
	}

	/**
	 * Get the 4 tiles surrounding the character to prevent checking collision with every tile
	 * 
	 * @return Array[4] of tiles if the caracter is on the map, Array[4] of null otherwise
	 */
	public Tile[] getSurroundingTiles(int x, int y) {
        Tile[] surroundingTiles = new Tile[4];

        int leftX = x + solidBoxCornersOffset.x;
        int rightX = x + solidBoxCornersOffset.x + solidBox.width;
        int topY = y + solidBoxCornersOffset.y;
        int bottomY = y + solidBoxCornersOffset.y + solidBox.height;
        
        int leftTileCol = leftX / Global.TILE_SIZE;
        int rightTileCol = rightX / Global.TILE_SIZE;
        int topTileRow = topY / Global.TILE_SIZE;
        int bottomTileRow = bottomY / Global.TILE_SIZE;

		if (leftTileCol >= 0 && rightTileCol < Global.COL_NUM && topTileRow >= 0 && bottomTileRow < Global.ROW_NUM ) {
			surroundingTiles[0] = mapTiles[topTileRow][leftTileCol]; // top left
			surroundingTiles[1] = mapTiles[topTileRow][rightTileCol]; // top right
			surroundingTiles[2] = mapTiles[bottomTileRow][leftTileCol]; // bottom left
			surroundingTiles[3] = mapTiles[bottomTileRow][rightTileCol]; // bottom right
		} else {
			surroundingTiles[0] = null;
			surroundingTiles[1] = null;
			surroundingTiles[2] = null;
			surroundingTiles[3] = null;
		}
        
        return surroundingTiles;
    }

	/**
	 * Used only for debugging to draw the surrounding tiles
	 */
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

	/**
	 * Make the character move to the x,y position if it is possible
	 */
	public void move(int x, int y) {
		if (canMove(x, y)) {
			// move the character
			this.position.x = x;
			this.position.y = y;
			// update the hit box
			this.hitbox.x = x + hitBoxCornersOffset.x;
			this.hitbox.y = y + hitBoxCornersOffset.y;
			// update the solid box
			this.solidBox.x = x + solidBoxCornersOffset.x;
			this.solidBox.y = y + solidBoxCornersOffset.y;
			// System.out.println(this + " moved to x: " + x + " y: " + y + " with speed: " + speed);
		}
		// System.out.println(this + " could not move to x: " + x + " y: " + y);
	}

	/**
	 * Check if the character can move to the given position
	 * 
	 * @return True if the character can move to the given position
	 */
	public boolean canMove(int x, int y) {
		// get the 4 tiles surrounding the x, y position to prevent checking collision with every tile
		Tile[] surondingTiles = getSurroundingTiles(x, y);

		for (int i = 0; i < surondingTiles.length; i++) {
			if (surondingTiles[i] != null) {
				// if the tile is solid, check if the character is colliding with it
				if (surondingTiles[i].isSolid()) {
					// predict the next position of the character
					Rectangle predictedSolidBox = new Rectangle(this.solidBox);
					predictedSolidBox.x = x + solidBoxCornersOffset.x;
					predictedSolidBox.y = y + solidBoxCornersOffset.y;
					// System.out.println("solidBox: " + solidBox + " predictedSolidBox: " + predictedSolidBox);

					// if the predicted position collides with the tile, return false
					if (surondingTiles[i].collidsWith(predictedSolidBox)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Make the character move up if possible and even if not possible upate the direction and last direction
	 */
	public void moveUp() {
		if (isSprinting) {
			move(position.x, position.y - sprintSpeed);
		} else {
			move(position.x, position.y - speed);
		}
		direction = lastDirection;
    }

	/**
	 * Make the character move down if possible and even if not possible upate the direction and last direction
	 */
	public void moveDown() {
		if (isSprinting) {
			move(position.x, position.y + sprintSpeed);
		} else {
			move(position.x, position.y + speed);
		}
		direction = lastDirection;
	}

	/**
	 * Make the character move left if possible and even if not possible upate the direction and last direction
	 */
	public void moveLeft() {
		if (isSprinting) {
			move(position.x - sprintSpeed, position.y);
		} else {
			move(position.x - speed, position.y);
		}
		direction = LEFT;
		lastDirection = LEFT;
	}

	/**
	 * Make the character move right if possible and even if not possible upate the direction and last direction
	 */
	public void moveRight() {
		if (isSprinting) {
			move(position.x + sprintSpeed, position.y);
		} else {
			move(position.x + speed, position.y);
		}
		direction = RIGHT;
		lastDirection = RIGHT;
	}

	/**
	 * Make the character idle
	 */
	public void idle() {
		direction = lastDirection + IDLE;
	}


	/**
	 * Attack the given character
	 */
	public void attack(Character character) {
		// TODO: Elsa
        System.out.println(this + " attacked " + character + " with damage: " + attackDamage);
    }
	
	protected abstract void die();

	public void draw(Graphics2D g) {
        if (Global.DEBUG) {
            // draw a filled circle centered at x, y
        	g.setColor(Color.RED);
			int r = 5;
			g.fillOval(position.x - r, position.y - r, r * 2, r * 2);
            
        	// draw hitbox
        	g.setColor(Color.BLUE);
        	g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        	// draw solidbox
        	g.setColor(Color.GREEN);
        	g.drawRect(solidBox.x, solidBox.y, solidBox.width, solidBox.height);

            // fill suronding squares with semi transparent yellow
            g.setColor(new Color(255, 255, 0, 100));
            int[][] surroundingColsRows = getSurroundingColsRows();
            for (int i = 0; i < surroundingColsRows.length; i++) {
                int col = surroundingColsRows[i][0];
                int row = surroundingColsRows[i][1];
                g.fillRect(col * Global.TILE_SIZE, row * Global.TILE_SIZE, Global.TILE_SIZE, Global.TILE_SIZE);
            }
        }
    }
}
