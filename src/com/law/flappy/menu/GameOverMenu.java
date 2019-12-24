package com.law.flappy.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.FlappyBird;
import com.law.flappy.art.Sprite;
import com.law.flappy.util.Score;
import com.law.flappy.util.Sound;

public class GameOverMenu extends Menu{
	
	private BufferedImage gameOver, scoreBoard, menu, start, sound, muted;
	
	private int gOverX, gOverY;
	private int sbX, sbY;
	
	private int velocity = 15;
	
	private boolean animFinished;
	
	private Score score;
	
	public GameOverMenu(Score score) {
		this.score = score;
		
		gameOver = Sprite.load("res/gameover.png");
		scoreBoard = Sprite.load("res/scoreboard.png");
		menu = Sprite.load("res/menu.png");
		start = Sprite.load("res/start.png");
		sound = Sprite.load("res/sound.png");
		muted = Sprite.load("res/muted.png");
		
		animFinished = false;
		
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
		} else {
			animFinished = true;
		}
		
		if(clicked && animFinished) {
			if(mouseX > 70 && mouseX < 170 && mouseY > 370 && mouseY < 405) {
				if(!Sound.muted) Sound.swooshing.Play();
				game.resetGame();
			}
			
			if(mouseX > 260 && mouseX < 360 && mouseY > 370 && mouseY < 405) {
				if(!Sound.muted) Sound.swooshing.Play();
				game.retry();
			}
			
			if(mouseX > 0 && mouseX < 38 && mouseY > 0 && mouseY < 40) {
				Sound.muted = !Sound.muted;
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(gameOver, gOverX, gOverY, 282, 57, null);
		g.drawImage(scoreBoard, sbX, sbY, 339, 174, null);
		
		if(animFinished) {
			g.drawImage(menu, 70, sbY + 120, 100, 35, null);
			g.drawImage(start, 260, sbY + 120, 100, 35, null);
			score.render(g, true);
		}
	
		if(Sound.muted) {
			g.drawImage(muted, 0, 0, 39, 42, null);
		} else {
			g.drawImage(sound, 0, 0, 39, 42, null);
		}
		
	}

}
