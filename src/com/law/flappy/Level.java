package com.law.flappy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Sprite;

public class Level {
	
	private int w_width, w_height;
	
	private BufferedImage background, ground, ground2;
	
	private int velocity = 2;
	private int groundX = 0;
	
	public Level(int w_width, int w_height) {
		this.w_width = w_width;
		this.w_height = w_height;
		
		background = Sprite.load("res/background.png");
		ground = Sprite.load("res/ground.png");
		ground2 = ground;
	}
	
	public void tick() {
		groundX += velocity;
		groundX = groundX % 420;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, w_width, w_height, null);
		g.drawImage(ground2, 420 - groundX, 570, 420, 150, null);
		g.drawImage(ground, -groundX, 570, 420, 150, null);
		
	}
	
	

}
