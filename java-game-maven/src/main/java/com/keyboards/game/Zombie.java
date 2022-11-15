package com.keyboards.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.keyboards.tile.Tile;

public class Zombie extends Mob {
    
    public Zombie(int col, int row, Tile[][] mapTiles) {
        super(col, row, mapTiles);
    }

    public Zombie(Tile[][] mapTiles) {
        super(mapTiles);
    }

    protected void initStats() {
        health = 100;
        attackDamage = 10;
        speed = 1;
    }

    protected void initHitBox() {
        hitBoxCornersOffset = new Point(0, 0);
        hitbox = new Rectangle(position.x + hitBoxCornersOffset.x, position.y + hitBoxCornersOffset.y, 32, 32);
    }

    protected void initSolidBox() {
        solidBoxCornersOffset = new Point(0, 0);
        solidBox = new Rectangle(position.x + solidBoxCornersOffset.x, position.y + solidBoxCornersOffset.y, 32, 32);
    }

    protected void initSprites() {
        // TODO gives sprites to the zombie
    }

    protected void die() {
        System.out.println("zombie died");
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);

        super.draw(g);
    }
}
