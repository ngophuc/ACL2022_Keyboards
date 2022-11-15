package com.keyboards.main;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import com.keyboards.engine.GameController;

public class Controller implements GameController{

	private HashMap<String, Boolean> commands = new HashMap<String, Boolean>();


	public Controller() {
		commands.put("UP", false);
		commands.put("DOWN", false);
		commands.put("LEFT", false);
		commands.put("RIGHT", false);
		commands.put("SPACE", false);
		commands.put("SHIFT", false);
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
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z: commands.put("UP", true); break;
			case KeyEvent.VK_S: commands.put("DOWN", true); break;
			case KeyEvent.VK_Q: commands.put("LEFT", true); break;
			case KeyEvent.VK_D: commands.put("RIGHT", true); break;
			case KeyEvent.VK_SHIFT: commands.put("SHIFT", true); break;
			case KeyEvent.VK_SPACE: commands.put("SPACE", true); break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_Z: commands.put("UP", false); break;
			case KeyEvent.VK_S: commands.put("DOWN", false); break;
			case KeyEvent.VK_Q: commands.put("LEFT", false); break;
			case KeyEvent.VK_D: commands.put("RIGHT", false); break;
			case KeyEvent.VK_SHIFT: commands.put("SHIFT", false); break;
			case KeyEvent.VK_SPACE: commands.put("SPACE", false); break;
		}
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}
}
