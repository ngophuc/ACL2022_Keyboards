package com.keyboards.main;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import com.keyboards.engine.GameController;

public class Controller implements GameController{

	private HashMap<String, Boolean> commands = new HashMap<String, Boolean>();


	public Controller() {
		//TODO: Loric
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public HashMap<String, Boolean> getCommand() {
		return this.commands;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		//TODO: Loric
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		//TODO: Loric
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}
}
