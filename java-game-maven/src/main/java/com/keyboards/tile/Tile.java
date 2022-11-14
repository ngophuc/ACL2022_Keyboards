package com.keyboards.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.keyboards.global.Global;

public class Tile {
	
	public BufferedImage image;
	public Rectangle solidBox;
	public int row;
	public int x;
	public int col;
	public int y;

	
	public Tile(int row, int col, BufferedImage image) {
		this.row = row;
		this.x = row * Global.TILE_SIZE;
		this.col = col;
		this.y = col * Global.TILE_SIZE;
		this.image = image;
		this.solidBox = null;
	}
	
	public Tile(int row, int col, BufferedImage image, Rectangle solidBox) {
		this.row = row;
		this.col = col;
		this.image = image;
		this.solidBox = solidBox;
	}
	
	public boolean isSolid() {
		return this.solidBox != null;
	}

	public boolean collidsWith(Rectangle solidBox) {
		return this.solidBox.intersects(solidBox);
	}
	
	public void draw(Graphics2D g, int col, int row) {
		g.drawImage(this.image, col * Global.TILE_SIZE, row * Global.TILE_SIZE, Global.TILE_SIZE, Global.TILE_SIZE, null);
		
		if (Global.DEBUG) {
			// draw the solid box
			g.setColor(Color.PINK);
			if (this.solidBox != null) {
				g.drawRect(this.solidBox.x,this.solidBox.y, this.solidBox.width, this.solidBox.height);
			}
		}
	}
	
}