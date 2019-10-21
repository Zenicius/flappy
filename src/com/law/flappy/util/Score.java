package com.law.flappy.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.law.flappy.art.Sprite;

public class Score {

	private BufferedImage[] numbers = { Sprite.load("res/0.png"), Sprite.load("res/1.png"), Sprite.load("res/2.png"),
			Sprite.load("res/3.png"), Sprite.load("res/4.png"), Sprite.load("res/5.png"), Sprite.load("res/6.png"),
			Sprite.load("res/7.png"), Sprite.load("res/8.png"), Sprite.load("res/9.png") };

	private int score;
	
	private int startX, startY;

	public Score() {
		score = 0;
		startX = 200;
		startY = 50;
	}

	public void increase() {
		Sound.point.Play();
		this.score ++;
	}

	public void render(Graphics2D g, boolean gameOver) {
		if(gameOver) {
			String aux = Integer.toString(score);
			int z = 1;
			int currentX = startX;
			for(int i = 0; i < aux.length(); i++) {
				int number = Integer.parseInt(aux.substring(i, z));
				g.drawImage(numbers[number], currentX, 315, 24, 30, null);
				z++;
				currentX += 25;
			}
			return;
		}

		String aux = Integer.toString(score);
		int z = 1;
		int currentX = startX;//- (aux.length() * 25) + 25;
		
		System.out.println("" + startX);
		
		for(int i = 0; i < aux.length(); i++) {
			int number = Integer.parseInt(aux.substring(i, z));
			g.drawImage(numbers[number], currentX, startY, 24, 30, null);
			z++;
			currentX += 25;
		}
	}

}
