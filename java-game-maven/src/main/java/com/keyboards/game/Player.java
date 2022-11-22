package com.keyboards.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.keyboards.graphics.Sprite;
import com.keyboards.graphics.SpriteSheet;
import com.keyboards.tile.Tile;


public class Player extends Character {
    ArrayList<Item> inventory = new ArrayList<Item>();

    public final int NUMBER_OF_FRAME_IN_WALK_ANIM = 6;
    		
    public final int RIGHT = 0;
    public final int LEFT = 1;
    public final int IDLE = 2;
    // public final int UP = 2;
    // public final int DOWN = 3;
    // public final int IDLE = 4;

    private Sprite[] idleLeft;
    private Sprite[] idleRight;
    private Sprite[] walkLeft;
    private Sprite[] walkRight;
    
    public int spriteNum = 0;
    public int spriteCounter = 0;

    public Player(int col, int row, Tile[][] mapTiles) {
        super(col, row, mapTiles);
    }

    public Player(Tile[][] mapTiles) {
        super(mapTiles);
    }

    protected void initStats() {
        health = 100;
        attackDamage = 20;
        speed = 1;
        sprintSpeed = 2;
    }

    public void initHitBox() {
        hitBoxCornersOffset = new Point(16*2, 13*2);
        hitbox = new Rectangle(position.x + hitBoxCornersOffset.x, position.y + hitBoxCornersOffset.y, 15*2, 20*2);
    }

    public void initSolidBox() {
        solidBoxCornersOffset = new Point(16*2, 25*2);
        solidBox = new Rectangle(position.x + solidBoxCornersOffset.x, position.y + solidBoxCornersOffset.y, 15*2, 6*2);
    }

    protected void initSprites() {
        SpriteSheet idleLeftSheet = new SpriteSheet("res/player/player-idle-left-strip.png", 48, 48);
        SpriteSheet idleRightSheet = new SpriteSheet("res/player/player-idle-right-strip.png", 48, 48);
        SpriteSheet walkLeftSheet = new SpriteSheet("res/player/player-walk-left-strip.png", 48, 48);
        SpriteSheet walkRightSheet = new SpriteSheet("res/player/player-walk-right-strip.png", 48, 48);

        idleLeft = idleLeftSheet.getSpriteArray();
        idleRight = idleRightSheet.getSpriteArray();
        walkLeft = walkLeftSheet.getSpriteArray();
        walkRight = walkRightSheet.getSpriteArray();
    }

    public void pickUp(Item item) {
        inventory.add(item);
        System.out.println("picked up item " + item);
    }

    protected void die() {
        System.out.println("player died");
    }

    public void draw(Graphics2D g) {
        // String directionString = "";

        BufferedImage image = null;
        
        if (direction == IDLE + RIGHT) {
            image = idleRight[spriteNum].image;
        //    directionString = "idle right";

        } else if (direction == IDLE + LEFT) {
            image = idleLeft[spriteNum].image;
        //    directionString = "idle left";

        } else if (direction == RIGHT) {
            image = walkRight[spriteNum].image;
        //    directionString = "walk right";

        } else if (direction == LEFT) {
            image = walkLeft[spriteNum].image;
        //    directionString = "walk left";
        }

        // System.out.println("direction: " + direction + " " + directionString);
        g.drawImage(image, position.x, position.y, image.getHeight()*2, image.getWidth()*2, null);

        super.draw(g);
    }
}