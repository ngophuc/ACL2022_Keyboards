package com.keyboards.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.keyboards.graphics.Sprite;
import com.keyboards.graphics.SpriteSheet;
import com.keyboards.tile.Tile;

public class Chest extends Object{

    private Inventory inventory = new Inventory(this, 5, 5);

    private final int NUMBER_OF_FRAME_IN_OPEN_ANIM = 4;
    private final int scale_factor = 2;

    private Sprite[] spriteArray;
    
    public int spriteNum = 0;
    public int spriteCounter = 0;

    private boolean oppening = false;
    private boolean closing = false;

    public Chest(int col, int row, Tile[][] mapTiles) {
        super(col, row, mapTiles);
        hasInventory = true;
        System.out.println("solidBox: " + solidBox + " hitbox: " + hitbox);
    }

    public Chest(Tile[][] mapTiles, float distance_min, Player p) {
        super(mapTiles, distance_min, p);
        hasInventory = true;
        System.out.println("solidBox: " + solidBox + " hitbox: " + hitbox);
    }

    public void initHitBox() {
        hitBoxCornersOffset = new Point(1,1 );
        hitbox = new Rectangle(position.x-(spriteArray[0].getHeight()/2)*scale_factor, position.y-2*(spriteArray[0].getWidth()/2),spriteArray[0].getHeight()*scale_factor - hitBoxCornersOffset.x*scale_factor,spriteArray[0].getWidth()*scale_factor- hitBoxCornersOffset.y*scale_factor);
    }

    public void initSolidBox() {
        solidBoxCornersOffset = new Point(1,1);
        solidBox = new Rectangle(position.x-(spriteArray[0].getHeight()/2)*scale_factor, position.y-(spriteArray[0].getWidth()/2)*scale_factor,spriteArray[0].getHeight()*scale_factor - hitBoxCornersOffset.x*scale_factor,spriteArray[0].getWidth()*scale_factor- hitBoxCornersOffset.y*scale_factor);
    }

    protected void initSprites() {
        SpriteSheet spriteSheet = new SpriteSheet("res/Objects/Chest.png", 16, 16);

        spriteArray = spriteSheet.getSpriteArray();
    }

    public boolean isOpen() { return inventory.isOpen(); }
    public void open() { oppening = true; spriteNum = 0; inventory.open(); }
    public void close() { closing = true; spriteNum = NUMBER_OF_FRAME_IN_OPEN_ANIM - 1; inventory.close(); }
    public void put(Item item) { inventory.addItem(item); }

    public void transfertClickedItem(int mouseX, int mouseY, Player player) {
        inventory.transfertClickedItem(mouseX, mouseY, player.getInventory());
    }

    public void drawInventory(Graphics2D g) { inventory.draw(g); }

    public void draw(Graphics2D g) {
        if (oppening || closing) {
            spriteCounter++;

            if (spriteCounter > 5) {
                if ((spriteNum < NUMBER_OF_FRAME_IN_OPEN_ANIM - 1 && oppening) || (spriteNum > 0 && closing)) {
                    spriteNum += oppening ? 1 : -1;
                } else {
                    if (oppening) {
                        spriteNum = NUMBER_OF_FRAME_IN_OPEN_ANIM - 1;
                    }
                    if (closing) {
                        spriteNum = 0;
                    }
                    oppening = false;
                    closing = false;
                }
                spriteCounter = 0;
            }

            g.drawImage(spriteArray[spriteNum].image,
                position.x-spriteArray[0].getHeight()/2*scale_factor,
                position.y-spriteArray[0].getHeight()/2*scale_factor,
                spriteArray[0].getHeight()*scale_factor,
                spriteArray[0].getHeight()*scale_factor,
                null
            );
        } else {
            spriteNum = 0;
            if (inventory.isOpen()) {
                g.drawImage(spriteArray[NUMBER_OF_FRAME_IN_OPEN_ANIM-1].image, 
                    position.x-spriteArray[0].getHeight()/2*scale_factor, 
                    position.y-spriteArray[0].getHeight()/2*scale_factor,
                    spriteArray[0].getHeight()*scale_factor,
                    spriteArray[0].getHeight()*scale_factor,
                    null
                );
            } else {
                g.drawImage(spriteArray[0].image,
                    position.x-spriteArray[0].getHeight()/2*scale_factor,
                    position.y-spriteArray[0].getHeight()/2*scale_factor,
                    spriteArray[0].getHeight()*scale_factor,
                    spriteArray[0].getHeight()*scale_factor,
                    null
                );
            }
        }
    }
    
}
