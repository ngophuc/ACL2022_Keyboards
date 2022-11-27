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
    public final int NUMBER_OF_FRAME_IN_ATTACK_ANIM = 4;
    		
    public final int RIGHT = 0;
    public final int LEFT = 1;
    public final int IDLE = 2;
    // public final int UP = 2;
    // public final int DOWN = 3;
    // public final int IDLE = 4;
    public boolean isAttacking = false;

    private Sprite[] idleLeft;
    private Sprite[] idleRight;
    private Sprite[] walkLeft;
    private Sprite[] walkRight;
    private Sprite[] attackLeft;
    private Sprite[] attackRight;
    
    public int spriteNum = 0;
    public int spriteAttack=0;
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
        SpriteSheet attackRightSheet = new SpriteSheet("res/player/player-attack-right-strip.png", 48, 48);
        SpriteSheet attackLeftSheet = new SpriteSheet("res/player/player-attack-left-strip.png", 48, 48);
        
        idleLeft = idleLeftSheet.getSpriteArray();
        idleRight = idleRightSheet.getSpriteArray();
        walkLeft = walkLeftSheet.getSpriteArray();
        walkRight = walkRightSheet.getSpriteArray();
        attackRight = attackRightSheet.getSpriteArray();
        attackLeft = attackLeftSheet.getSpriteArray();
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
        
        if ((direction == LEFT || lastDirection == LEFT) && isAttacking) {
        	image = attackLeft[spriteAttack].image;
        } else if ((direction == RIGHT || lastDirection == RIGHT) && isAttacking) {
        	image = attackRight[spriteAttack].image;
        }

        if (isAttacking) {
        	
        	spriteCounter++;
    		if (spriteCounter > 5) {
    			if (spriteAttack < NUMBER_OF_FRAME_IN_ATTACK_ANIM - 1) {
    				spriteAttack++;
    			} else {
    				isAttacking = false;
    				spriteAttack = 0;
    			}
    			spriteCounter = 0;
    		}
    		
        	g.drawImage(image, position.x, position.y, image.getHeight()*2, image.getWidth()*2, null);        	
        	
        } else {
        	
        	spriteCounter++;
    		if (spriteCounter > 5) {
    			if (spriteNum < NUMBER_OF_FRAME_IN_WALK_ANIM - 1) {
    				spriteNum++;
    			} else {
    				spriteNum = 0;
    			}
    			spriteCounter = 0;
    		}
        	
        	// System.out.println("direction: " + direction + " " + directionString);
        	g.drawImage(image, position.x, position.y, image.getHeight()*2, image.getWidth()*2, null);        	
        }

        super.draw(g);
    }
}