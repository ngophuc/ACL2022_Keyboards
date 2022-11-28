package com.keyboards.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.keyboards.global.Global;
import com.keyboards.graphics.SpriteSheet;
import com.keyboards.tile.Tile;

public class Speed_potion extends Item {

	public Speed_potion(int col, int row, Tile[][] mapTiles, boolean isInInventory) {
		super(col, row, mapTiles, isInInventory);
		this.skillIncrease = 2;
	}
	
	public Speed_potion (Tile[][] mapTiles, float distance_min, Player p, boolean isInInventory) {
		super(mapTiles, distance_min, p, isInInventory);
		this.skillIncrease = 2;
	}

	public void use(Character character) {
		character.speed += this.skillIncrease;
		System.out.println("used " + this.getClass().getSimpleName());
	}

	public void initHitBox() {
		hitBoxCornersOffset = new Point(3,3);
		hitbox = new Rectangle(position.x-this.image.getHeight()/2, position.y-this.image.getWidth()/2,this.image.getHeight() - hitBoxCornersOffset.x,this.image.getWidth()- hitBoxCornersOffset.y);
	}

	public void initSolidBox() {
		solidBoxCornersOffset = new Point(3,3);
		solidBox = new Rectangle(position.x-this.image.getHeight()/2, position.y-this.image.getWidth()/2,this.image.getHeight() - hitBoxCornersOffset.x,this.image.getWidth()- hitBoxCornersOffset.y);
	}

	protected void initSprites() {
		SpriteSheet Sprite = new SpriteSheet("res/Objects/Speed-potion.png", 32, 32);

		sprite = Sprite.getSpriteArray();
		this.image = sprite[0].image;
	}


	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(this.image, x, y,this.image.getHeight(),image.getWidth(), null);
    }

    public void draw(Graphics2D g) {
        g.drawImage(this.image, position.x-this.image.getHeight()/2, position.y-this.image.getWidth()/2,this.image.getHeight(),image.getWidth(), null);

		if (Global.DEBUG) {
			g.setColor(Color.BLUE);
			g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
			g.setColor(Color.RED);
			g.drawRect(solidBox.x, solidBox.y, solidBox.width, solidBox.height);
		}
    }
}