package com.keyboards.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.keyboards.global.Global;

public abstract class Entity {
	public Point position = new Point(0,0);
	public Rectangle hitbox;
	public Point hitBoxCornersOffset;
	public Rectangle solidBox;
	public Point solidBoxCornersOffset;
	public boolean hasInventory = false;
	public abstract void draw(Graphics2D g);


	/**
	 * @return The col the entity is in
	 */
	public int getX() {
		// get the center of the hitbox
		return (hitbox.x + (hitbox.width / 2));
	}

	/**
	 * @return The row the entity is in
	 */
	public int getY() {
		// get the center of the hitbox
		return (hitbox.y + (hitbox.height / 2));
	}

	/**
	 * @return The col the entity is in
	 */
	public int getCol() {
		// get the center of the hitbox and divide by tile size to get the col
		return getX() / Global.TILE_SIZE;
	}

	/**
	 * @return The row the entity is in
	 */
	public int getRow() {
		// get the center of the hitbox and divide by tile size to get the row
		return getY() / Global.TILE_SIZE;
	}

	/**
	 * @return True if the entity collides with another entity
	 */
	public boolean collidesWith(Entity entity) {
		return hitbox.intersects(entity.hitbox);
	}
}
