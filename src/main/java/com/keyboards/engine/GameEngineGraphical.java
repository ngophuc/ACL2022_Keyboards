package com.keyboards.engine;

import java.util.HashMap;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	private int fps;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController, int fps) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.fps = fps;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		double updateInterval = 1000000000 / this.fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime = 0;

		long timer = 0;
		int updates = 0;

		// boucle de game
		while (!this.game.isFinished()) {

			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / updateInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;

			if (delta >= 1) {
				// demande controle utilisateur
				HashMap<String, Boolean> userCmd = this.gameController.getCommand();

				// fait evoluer le game
				this.game.evolve(userCmd);

				// affiche le game
				this.gui.paint();

				delta--;
				updates++;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + updates);
				updates = 0;
				timer = 0;
			}

		}
	}

}
