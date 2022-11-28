package com.keyboards.engine;

import java.util.HashMap;

/**
 * Based on the code of Horatiu Cirstea and Vincent Thomas
 */
public interface Game {

	
	/**
	 * Update the game depending on the commands
	 */
	public void evolve(HashMap<String, Boolean> userCmd, GameMouseHandler mouseHandler);
	
	/**
	 * @return true if the game is over
	 */
	public boolean isFinished();
	
}
