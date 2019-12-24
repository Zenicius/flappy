package com.law.flappy.util;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hitbox {

	private double xTopLeft, yTopLeft;
	private double xBottomRight, yBottomRight;

	public Hitbox(double xTopLeft, double yTopLeft, double xBottomRight, double yBottomRight) {
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;

		this.xBottomRight = xBottomRight;
		this.yBottomRight = yBottomRight;
	}

	public void tick(double x, double y) {
		this.xTopLeft += x;
		this.xBottomRight += x;

		this.yTopLeft += y;
		this.yBottomRight += y;
	}
	
	public void tick(double xTopleft, double yTopLeft, double xBottomRight, double yBottomRight) {
		this.xTopLeft = xTopleft;
		this.yTopLeft = yTopLeft;
		
		this.xBottomRight = xBottomRight;
		this.yBottomRight = yBottomRight;
	}
	
	public void render(Graphics2D g) {
		int x = (int) this.xTopLeft;
		int y = (int) this.yTopLeft;
		int width = (int)xBottomRight - (int)xTopLeft;
		int height = (int)yBottomRight - (int) yTopLeft;
		

		g.setColor(Color.RED);
		g.drawRect(x, y, width, height);
	}

	public boolean collision(Hitbox target) {
		double width = ((xBottomRight - xTopLeft) + (target.xBottomRight - target.xTopLeft)) / 2;
		double height = ((yBottomRight - yTopLeft) + (target.yBottomRight - target.yTopLeft)) / 2;
		double x = ((xBottomRight + xTopLeft) - (target.xTopLeft + target.xBottomRight)) / 2;
		double y = ((yBottomRight + yTopLeft) - (target.yTopLeft + target.yBottomRight)) / 2;
		
		if(Math.abs(x) <= width && Math.abs(y) <= height) {
			return true;
		}
		
		return false;
	}

}
