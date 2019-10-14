package com.law.flappy.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Sprite;

public class Pipe {

	private int x, y, direction;
	private double velocity = -2;
	
	private BufferedImage pipeDown = Sprite.load("res/pipe1.png");
	private BufferedImage pipeUp = Sprite.load("res/pipe2.png");

	public Pipe(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void tick(double delta) {
		x += velocity;
	}
	
	public void render(Graphics2D g) {
		if(direction == 0) {
			g.drawImage(pipeDown, x, y - 500, 78, 500, null);
		} else if(direction == 1) {
			g.drawImage(pipeUp, x, y - 500, 78, 500, null);
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}
