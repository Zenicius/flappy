package com.law.flappy.menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.FlappyBird;
import com.law.flappy.art.Animation;
import com.law.flappy.art.Sprite;
import com.law.flappy.util.Sound;

public class MainMenu extends Menu {
	
	private BufferedImage logo, start;
	private BufferedImage[] birdSprites = {Sprite.load("res/bird.png"), Sprite.load("res/bird2.png"), Sprite.load("res/bird3.png")};
	
	private Animation birdAnim;
	
	
	public MainMenu() {
		logo = Sprite.load("res/logo.png");
		start = Sprite.load("res/start.png");
		
		birdAnim = new Animation(birdSprites, 5);
		birdAnim.start();
	}
	
	public void tick(double delta, boolean clicked, int mouseX, int mouseY, FlappyBird game) {
		birdAnim.tick();
		
		if(clicked) {
			if(mouseX > 160 && mouseX < 280 && mouseY > 275 && mouseY <  317) {
				Sound.swooshing.Play();
				game.startGame();
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.drawImage(logo, 35, 120, 288, 66, null);
		g.drawImage(birdAnim.getSprite(), 335, 130, 51, 36, null);
		g.drawImage(start, 160, 290, 120, 42, null);
	}

}
