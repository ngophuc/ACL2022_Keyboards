package com.keyboards.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.keyboards.engine.GamePainter;
import com.keyboards.global.Global;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class Painter implements GamePainter {
	
	protected RunGame game;

	// strok with a width of 2, normal cap and join
    private final Stroke tileStroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
	
	/**
	 * la taille des cases
	 */

	/**
	 * appelle constructeur parent
	 * 
	 * @param game
	 *            le jeutest a afficher
	 */
	public Painter(RunGame game) {
		this.game = game;
	}
	
	
	private void drawGrid(Graphics2D g) {
        Stroke oldStroke = g.getStroke();

        g.setStroke(tileStroke);
        g.setColor(new Color(78, 108, 80));
        for (int y = 0; y < Global.ROW_NUM; y++) {
            for (int x = 0; x < Global.COL_NUM; x++) {
                int nx = x * Global.TILE_SIZE;
                int ny = y * Global.TILE_SIZE;
                g.drawRect(nx, ny, Global.TILE_SIZE, Global.TILE_SIZE);
            }
        }

        g.setStroke(oldStroke);
    }

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) {
		Graphics2D g = (Graphics2D) im.getGraphics();

		// draw grid for tests
        drawGrid(g);

		game.tileManager.draw(g);
	}

	@Override
	public int getWidth() {
		return Global.WIDTH;
	}

	@Override
	public int getHeight() {
		return Global.HEIGHT;
	}
}
