package com.keyboards.engine;

import java.util.HashMap;

/**
 * Based on the code of Horatiu Cirstea and Vincent Thomas
 */
public class GameEngineGraphical {

	/**
	 * the game to run
	 */
	private Game game;

	/**
	 * the painter to use
	 */
	private GamePainter gamePainter;

	/**
	 * the controller to use
	 */
	private GameController gameController;

	/**
	 * the graphical user interface
	 */
	private GraphicalInterface gui;

	private int fps;
	private GameMouseHandler mouseHandler;

	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController, GameMouseHandler mouseHandler, int fps) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.mouseHandler = mouseHandler;
		this.fps = fps;
	}

	/**
	 * run the game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter, this.gameController, this.mouseHandler);

		double updateInterval = 1000000000 / this.fps; // in nanoseconds
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
				this.game.evolve(userCmd, mouseHandler);

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
