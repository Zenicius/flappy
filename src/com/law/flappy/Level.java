package com.law.flappy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.law.flappy.art.Sprite;
import com.law.flappy.entities.Pipe;
import com.law.flappy.util.Hitbox;

public class Level {
	
	private int w_width, w_height;
	
	private BufferedImage background, ground, ground2;
	
	private int velocity = 2;
	private int groundX = 0;
	
	private Hitbox groundHb;
	private boolean showHitbox = false;
	
	public Level(int w_width, int w_height) {
		this.w_width = w_width;
		this.w_height = w_height;
		
		background = Sprite.load("res/background.png");
		ground = Sprite.load("res/ground.png");
		ground2 = ground;
		
		groundHb = new Hitbox(0, 620, 420, 770);
	}
	
	public void tick(double delta, boolean gameStarted, boolean gameOver, ArrayList<Pipe> pipes) {
		if(gameOver) return;
		
		groundX += velocity;
		groundX = groundX % 420;
		
		if(gameStarted) {
			for(int i = 0; i < pipes.size(); i++) {
				pipes.get(i).tick(delta);
			}
		}
	}
	
	public void render(Graphics2D g, boolean gameStarted, ArrayList<Pipe> pipes) {
		g.drawImage(background, 0, 0, w_width, w_height, null);
		
		if(gameStarted) {
			for(int i = 0; i < pipes.size(); i++) {
				pipes.get(i).render(g, this.showHitbox);
			}
		}
		
		g.drawImage(ground2, 420 - groundX, 620, 420, 150, null);
		g.drawImage(ground, -groundX, 620, 420, 150, null);
		
		if(showHitbox) groundHb.render(g);
	}
	
	public Hitbox getGroundHb() {
		return this.groundHb;
	}
	
	public void showHitbox(boolean option) {
		this.showHitbox = option;
	}
	

}
