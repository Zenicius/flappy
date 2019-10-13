package com.law.flappy.art;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	private static BufferedImage sprite;
	
	public static BufferedImage load(String path) {
		try {
			System.out.println("Loading: " + path);
			BufferedImage image = ImageIO.read(new File(path));
			sprite = image;
			return sprite;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public static BufferedImage crop(int x, int y, int tileSize) {
		if(sprite == null) {
			System.out.println("No image loaded");
			return null;
		}
		
		BufferedImage croped = sprite.getSubimage(x, y, tileSize, tileSize);
		
		return croped;
		
	}

}
