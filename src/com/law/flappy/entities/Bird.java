package com.law.flappy.entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Animation;
import com.law.flappy.art.Sprite;
import com.law.flappy.util.Hitbox;
import com.law.flappy.util.Sound;

public class Bird{
	
	private int x, y;
	private double rotationValue = 10;
	
	private double velocity;
	private double G = 0.5;
	private double flapValue = -7;
	
	private BufferedImage[] sprites = {Sprite.load("res/bird.png"), Sprite.load("res/bird2.png"), Sprite.load("res/bird3.png")};
	private Animation birdAnimation;
	
	private Hitbox hb;
	private boolean showHitbox = false;
	
	private boolean stopMovement = false;
	
	public Bird(int x, int y) {
		this.x = x;
		this.y = y;

		hb = new Hitbox(x, y, x + 51, y + 36);
		
		birdAnimation = new Animation(sprites, 5);
		birdAnimation.start();
	}
	
	public void flap() {
		velocity = flapValue;
		if(!Sound.muted) Sound.wing.Play();
	}
	
	public void tick(boolean action, boolean clicked, boolean gameOver, double delta) {
		if(stopMovement) return;
		if(!gameOver) birdAnimation.tick();
		
		velocity += G * delta;
		this.y += velocity * delta;
		
		if(clicked && !gameOver) flap();
		
		hb.tick(x, y, x + 51, y + 36);
	}
	
	public void render(Graphics2D g) {
		if(showHitbox) hb.render(g);
		
		AffineTransform Tx = g.getTransform();
		g.rotate(Math.atan(velocity / rotationValue), x + 51 / 2, y + 36 / 2);
		g.drawImage(birdAnimation.getSprite(), x, y, 51, 36, null);
		g.setTransform(Tx);
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
	
	public void showHitbox(boolean option) {
		this.showHitbox = option;
	}
	
	public void stopMovement() {
		this.stopMovement = true;
	}

}
