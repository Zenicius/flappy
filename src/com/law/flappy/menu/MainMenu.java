package com.law.flappy.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.law.flappy.FlappyBird;
import com.law.flappy.art.Sprite;

public class MainMenu extends Menu {
	
	BufferedImage logo, bird, start;
	
	
	public MainMenu() {
		logo = Sprite.load("res/logo.png");
		bird = Sprite.load("res/bird.png");
		start = Sprite.load("res/start.png");
		
	}
	
	public void tick(boolean clicked, int mouseX, int mouseY, FlappyBird game) {
		if(clicked) {
			if(mouseX > 160 && mouseX < 280 && mouseY > 275 && mouseY <  317) {
				game.startGame();
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(logo, 35, 120, 288, 66, null);
		g.drawImage(bird, 335, 130, 51, 36, null);
		g.drawImage(start, 160, 290, 120, 42, null);
	}

}
