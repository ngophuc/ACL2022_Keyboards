package com.keyboards.start;

import com.keyboards.engine.GameEngineGraphical;
import com.keyboards.global.Global;
import com.keyboards.main.Controller;
import com.keyboards.main.Painter;
import com.keyboards.main.RunGame;

/**
 * Main class to start the game.
 */
public class Main {
    
	public static void main(String[] args) throws InterruptedException {
		Global.DEBUG = true;

		// create the game engine's objects
		RunGame game = new RunGame("helpFile.txt");
		Painter painter = new Painter(game);
		Controller controller = new Controller();

		// launch the game engine with graphical interface at 60 fps
		GameEngineGraphical engine = new GameEngineGraphical(game, painter, controller, 60);
		engine.run();
	}

}
