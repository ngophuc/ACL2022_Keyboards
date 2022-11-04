package com.keyboards.engine;

import java.awt.event.KeyListener;
import java.util.HashMap;

	/**
	 * @author Horatiu Cirstea
	 * 
	 * controleur qui envoie des commandes au jeu 
	 * 
	 */
	public interface GameController extends KeyListener {

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public HashMap<String, Boolean> getCommand();

}
