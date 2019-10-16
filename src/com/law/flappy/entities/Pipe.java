package com.law.flappy.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Sprite;
import com.law.flappy.util.Hitbox;

public class Pipe {

	private int x, y, direction;
	private double velocity = -2;
	
	private BufferedImage pipeDown = Sprite.load("res/pipe1.png");
	private BufferedImage pipeUp = Sprite.load("res/pipe2.png");
	
	private Hitbox hb; 

	public Pipe(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;

		hb = new Hitbox(x, y, x + 78, y + 500);
	}
	
	public void tick(double delta) {
		x += velocity;
		hb.tick(velocity, 0);
	}
	
	public void render(Graphics2D g, boolean showHitbox) {
		if(showHitbox) hb.render(g);
		
		if(direction == 0) {
			g.drawImage(pipeDown, x, y, 78, 500, null);
		} else if(direction == 1) {
			g.drawImage(pipeUp, x, y, 78, 500, null);
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Hitbox getHitbox() {
		return this.hb;
	}

}
