package com.keyboards.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.keyboards.engine.Game;
import com.keyboards.game.Entity;
import com.keyboards.game.Ghost;
import com.keyboards.game.Player;
import com.keyboards.game.Attack_potion;
import com.keyboards.game.Character;
import com.keyboards.game.Shield_potion;
import com.keyboards.game.Life_potion;
import com.keyboards.game.Mob;
import com.keyboards.game.Speed_potion;
import com.keyboards.game.Treasure;
import com.keyboards.game.Zombie;
import com.keyboards.tile.TileManager;

public class RunGame implements Game {
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Mob> mobs = new ArrayList<Mob>();

	TileManager tileManager = new TileManager();
	
	Player player = new Player(tileManager.mapTiles);
	Ghost ghost = new Ghost(tileManager.mapTiles);
	Zombie zombie = new Zombie(tileManager.mapTiles);
	Treasure tresor = new Treasure(tileManager.mapTiles,15,player);
	Life_potion life_potion=new Life_potion(tileManager.mapTiles,15,player);
	Attack_potion attack_potion=new Attack_potion(tileManager.mapTiles,15,player);
	Shield_potion shield_potion=new Shield_potion(tileManager.mapTiles,15,player);
	Speed_potion speed_potion=new Speed_potion(tileManager.mapTiles,15,player);
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

		mobs.add(zombie);
		mobs.add(ghost);
		
		for (Entity mob : mobs) {
			entities.add(mob);
		}
		
		entities.add(player);
		entities.add(tresor);
		entities.add(life_potion);
		entities.add(attack_potion);
		entities.add(speed_potion);
		entities.add(shield_potion);
	}
	
	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(HashMap<String, Boolean> commands) {
		// String commandsString = "";
		
		if (commands.get("UP")) {
			// commandsString += "UP ";

			player.moveUp();
		}
		if (commands.get("DOWN")) {
			// commandsString += "DOWN ";

			player.moveDown();
		}
		if (commands.get("LEFT")) {
			// commandsString += "LEFT ";

			player.moveLeft();
		}
		if (commands.get("RIGHT")) {
			// commandsString += "RIGHT ";

			player.moveRight();
		}
		if (commands.get("SHIFT")) {
			player.isSprinting = true;
		} else {
			player.isSprinting = false;
		}
		if (commands.get("SPACE")) {
			// commandsString += "SPACE ";
			// TODO : use object
		}
		if (Controller.isIdle(commands)) {
			// commandsString += "IDLE ";
			player.idle();
		}

		if (commands.get("ATTACK")) {
			player.isAttacking = true;
		}

		if(player.isAttacking) {
			for (Mob mob : mobs) {
				if (player.canAttack(mob)) {
					System.out.println("inAttackRange");
					player.attack(mob);
				}
			}
		}
		
		// System.out.println("Commandes : " + commandsString);
		/*
		player.spriteCounter++;
		if (player.spriteCounter > 5) {
			if (player.spriteNum < player.NUMBER_OF_FRAME_IN_WALK_ANIM - 1) {
				player.spriteNum++;
			} else {
				player.spriteNum = 0;
			}
			player.spriteCounter = 0;
		}
		*/
		
		zombie.moveTowards(player);
		if (zombie.collidesWith(player)) {
			zombie.attack(player);
		}
		// ghost.moveTowards(player);
		// if (ghost.collidesWith(player)) {
		// 	ghost.attack(player);
		// }
		
//		System.out.println("sprite counter : " + player.spriteCounter + " sprite num : " + player.spriteNum);
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
