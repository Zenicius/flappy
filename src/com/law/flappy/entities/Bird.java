package com.law.flappy.entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Animation;
import com.law.flappy.art.Sprite;

public class Bird {
	
	private int x, y;
	private double rotationValue = 10;
	
	private double velocity;
	private double G = 0.5;
	private double flapValue = -7;
	
	private BufferedImage[] sprites = {Sprite.load("res/bird.png"), Sprite.load("res/bird2.png"), Sprite.load("res/bird3.png")};
	private Animation birdAnimation;
	
	public Bird(int x, int y) {
		birdAnimation = new Animation(sprites, 5);
		
		this.x = x;
		this.y = y;
		
		birdAnimation.start();
	}
	
	public void flap() {
		velocity = flapValue;
	}
	
	public void tick(boolean action, boolean clicked, double delta) {
		birdAnimation.tick();
		
		velocity += G * delta;
		this.y += velocity * delta;
		
		if(clicked) flap();
	}
	
	public void render(Graphics2D g) {
		AffineTransform Tx = g.getTransform();
		g.rotate(Math.atan(velocity / rotationValue), x + 51 / 2, y + 36 / 2);
		g.drawImage(birdAnimation.getSprite(), x, y, 51, 36, null);
		g.setTransform(Tx);
	}

}
