package com.law.flappy.art;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	
	private int delay;
	private int count;
	private int currentSprite;
	private int totalSprites;
	private boolean stopped;
	
	private List<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	public Animation(BufferedImage[] sprites, int delay) {
		this.stopped = true;
		
		this.delay = delay;
		this.count = 0;
		this.currentSprite = 0;
		
		for (int i = 0; i < sprites.length; i++) {
			this.sprites.add(sprites[i]);
		}
		
		this.totalSprites = this.sprites.size();		
	}
	
	public void start() {
		if(!stopped || totalSprites == 0) return;
		
		stopped = false;

	}
	
	public void stop() {
		if(stopped || totalSprites == 0) return;
		
		stopped = true;
	}
	
	public BufferedImage getSprite() {
		return sprites.get(currentSprite);
	}
	
	public void tick() {
		if(!stopped) {
			count++;
			
			if(count > delay) {
				count = 0;
				currentSprite++;
				
				if(currentSprite > totalSprites - 1) {
					currentSprite = 0;
				} 
			}
		}
	}

	
	
	
	

}
