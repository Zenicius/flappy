package com.law.flappy.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.FlappyBird;
import com.law.flappy.art.Sprite;

public class GameOverMenu extends Menu{
	
	private BufferedImage gameOver, scoreBoard;
	
	private int gOverX, gOverY;
	private int sbX, sbY;
	
	private int velocity = 15;
	
	public GameOverMenu() {
		gameOver = Sprite.load("res/gameover.png");
		scoreBoard = Sprite.load("res/scoreboard.png");
		
		gOverX = 70;
		gOverY = -100;
		
		sbX = 45;
		sbY = 825;
	}
	
	public void tick(double delta, boolean clicked, int mouseX, int mouseY, FlappyBird game) {
		if(gOverY < 150) {
			gOverY += velocity * delta;
		} 
		
		if(sbY > 255) {
			sbY -= velocity * delta;
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(gameOver, gOverX, gOverY, 282, 57, null);
		g.drawImage(scoreBoard, sbX, sbY, 339, 174, null);
	}

}
