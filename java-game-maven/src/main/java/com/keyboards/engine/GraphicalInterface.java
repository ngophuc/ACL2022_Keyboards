package com.keyboards.engine;

import javax.swing.JFrame;


/**
 * Based on the code of Horatiu Cirstea and Vincent Thomas
 */
public class GraphicalInterface  {

	/**
	 * the panel to draw on
	 */
	private DrawingPanel panel;
	
	/**
	 * the constructor for the gui: JFrame with a DrawingPanel for the game
	 */
	public GraphicalInterface(GamePainter gamePainter, GameController gameController, GameMouseHandler gameMouseHandler) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(gamePainter.getWidth(),gamePainter.getHeight());
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		
		// the panel to draw on
		this.panel = new DrawingPanel(gamePainter);
		f.setContentPane(this.panel);
		
		// bind the controller to the panel
		this.panel.addKeyListener(gameController);

		// bind the mouse handler to the panel
		this.panel.addMouseListener(gameMouseHandler);
		this.panel.addMouseMotionListener(gameMouseHandler);
		
		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
		f.getContentPane().setFocusTraversalKeysEnabled(false); // need to set to false for the TAB key to work
	}
	
	/**
	 * update the drawing
	 */
	public void paint() {
		this.panel.drawGame();	
	}
	
}
