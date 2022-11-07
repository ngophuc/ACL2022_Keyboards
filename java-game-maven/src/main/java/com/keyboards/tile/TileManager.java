package com.keyboards.tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.lang.model.element.NestingKind;

import com.keyboards.global.Global;

public class TileManager {
	
	public ArrayList<Tile> tiles;
	public Tile[][] mapTiles;

	public TileManager() {
		tiles = new ArrayList<Tile>();
		mapTiles = new Tile[Global.COL_NUM + 1][Global.ROW_NUM + 1];
		
		loadTileArrayFromDir("/res/tiles");
	}
	
	private BufferedImage loadTileImage(File file) {
		BufferedImage tileImage = null;
        try {
			System.out.println("Loading: " + file + "...");
            tileImage = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
            System.err.println(e);
        }
        return tileImage;
	}
	
	// need to figure out a way to properly order the tiles
	private void loadTileArrayFromDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				tiles.add(new Tile(loadTileImage(files[i]), new Rectangle(0, 0, Global.TILE_SIZE, Global.TILE_SIZE/2)));
			}
		} else {
			System.out.println("ERROR: not a directory: " + dirPath);
		}
	}
	
	public Tile geTile(int col, int row) {
		return mapTiles[row][col];
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile tile = tiles.get(i);
			if (tile != null) {
				if (i == 3) {
					mapTiles[i+1][5] = tile;
					tile.solidBox.x = (i+1) * Global.TILE_SIZE;
				} else {
					mapTiles[i][5] = tile;
					tile.solidBox.x = i * Global.TILE_SIZE;
				}
				tile.solidBox.y = 5 * Global.TILE_SIZE + Global.TILE_SIZE/2;
			}
		}

		for (int i = 0; i < Global.COL_NUM; i++) {
			for (int j = 0; j < Global.ROW_NUM; j++) {
				if (mapTiles[i][j] != null) {
					mapTiles[i][j].draw(g, i, j);
				}
			}
		}
	}

}