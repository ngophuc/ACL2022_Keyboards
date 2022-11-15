package com.keyboards.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.keyboards.engine.Game;
import com.keyboards.game.Entity;
import com.keyboards.game.Ghost;
import com.keyboards.game.Player;
import com.keyboards.game.Treasure;
import com.keyboards.game.Zombie;
import com.keyboards.tile.TileManager;

public class RunGame implements Game {
	
	ArrayList<Entity> entities = new ArrayList<Entity>();

	TileManager tileManager = new TileManager();
	
	Ghost ghost = new Ghost(tileManager.mapTiles);
	Zombie zombie = new Zombie(tileManager.mapTiles);

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public RunGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		
		entities.add(zombie);
		entities.add(ghost);
	}
	
	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(HashMap<String, Boolean> commands) {
		// TODO: Loric
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}
}
