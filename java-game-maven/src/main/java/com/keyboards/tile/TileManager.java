package com.keyboards.tile;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.keyboards.global.Global;

public class TileManager {
	
	public ArrayList<BufferedImage> tilesImages;
	public Tile[][] mapTiles;

	public TileManager() {
		tilesImages = new ArrayList<BufferedImage>();
		mapTiles = new Tile[Global.ROW_NUM][Global.COL_NUM];
		
		loadTileArrayFromDir("res/tiles");

		initMap();
		System.out.println("Map initialized");
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


	private void loadTileArrayFromDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			// sort the files alphabetically to ensure that the tiles are loaded in a predictable order
			for (int i = 0; i < files.length; i++) {
				for (int j = i + 1; j < files.length; j++) {
					if (files[i].getName().compareTo(files[j].getName()) > 0) {
						File temp = files[i];
						files[i] = files[j];
						files[j] = temp;
					}
				}
			}
			for (int i = 0; i < files.length; i++) {
				tilesImages.add(loadTileImage(files[i]));
			}
			System.out.println(tilesImages.size() + " tiles loaded");
		} else {
			System.out.println("ERROR: not a directory: " + dirPath);
		}
	}
	
	// needs to be in map class
	public Tile getTile(int row, int col) {
		return mapTiles[row][col];
	}

	private void initMap() {
	    System.out.println("Initializing map...");
		for (int row = 0; row < Global.ROW_NUM; row++) {
			for (int col = 0; col < Global.COL_NUM; col++) {
				mapTiles[row][col] = new Tile(row, col, null);
			}
		}

		for (int i = 0; i < tilesImages.size(); i++) {
			BufferedImage tileImage = tilesImages.get(i);
			if (tileImage != null) {
				if (i == 3) {
					mapTiles[5][i+1] = new Tile(5, i + 1, tileImage, new Rectangle((i+1) * Global.TILE_SIZE, 5 * Global.TILE_SIZE, Global.TILE_SIZE, Global.TILE_SIZE));
				} else {
					mapTiles[5][i] = new Tile(5, i, tileImage, new Rectangle(i * Global.TILE_SIZE, 5 * Global.TILE_SIZE, Global.TILE_SIZE, Global.TILE_SIZE));
				}
			}
		}
	}

	public void draw(Graphics2D g) {

		for (int row = 0; row < Global.ROW_NUM; row++) {
			for (int col = 0; col < Global.COL_NUM; col++) {
				if (mapTiles[row][col] != null) {
					mapTiles[row][col].draw(g, col, row);
				}
			}
		}
	}

}