package com.keyboards.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.keyboards.global.Global;
import com.keyboards.graphics.Sprite;
import com.keyboards.graphics.SpriteSheet;
import com.keyboards.tile.Tile;

public class Treasure extends Object {
	
	
	int coef_agrandissement_image=2;
    public Treasure(int col, int row, Tile[][] mapTiles) {
     //TODO place the object in the given col and row
	
    	
    	super(col, row, mapTiles);

}
    public Treasure(Tile[][] mapTiles,float distance_min,Player p) {
        //TODO place the object in the given col and row
   	
       	
       	super(mapTiles,distance_min,p);

   }

    public void initHitBox() {
        hitBoxCornersOffset = new Point(1,1 );
        hitbox = new Rectangle(position.x-(this.image.getHeight()/2)*2, position.y-2*(this.image.getWidth()/2),this.image.getHeight()*2 - hitBoxCornersOffset.x*2,this.image.getWidth()*2- hitBoxCornersOffset.y*2);
    }

    public void initSolidBox() {
        solidBoxCornersOffset = new Point(1,1);
        solidBox = new Rectangle(position.x-(this.image.getHeight()/2)*2, position.y-(this.image.getWidth()/2)*2,this.image.getHeight()*2 - hitBoxCornersOffset.x*2,this.image.getWidth()*2- hitBoxCornersOffset.y*2);
    }
    
    protected void initSprites() {
        SpriteSheet Sprite = new SpriteSheet("res/Objects/Tresor.png",16,16);
       
        super.sprite = Sprite.getSpriteArray();
        this.image = sprite[0].image;
    }
    
    
    public void draw(Graphics2D g) {

            g.drawImage(this.image, position.x-(this.image.getHeight()/2)*2, position.y-(this.image.getWidth()/2)*2,this.image.getHeight()*2,image.getWidth()*2, null);
            g.setColor(Color.BLUE);
        	g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    	
    }
   

}






