package com.law.flappy;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.law.flappy.entities.Bird;
import com.law.flappy.input.InputHandler;
import com.law.flappy.menu.MainMenu;
import com.law.flappy.menu.Menu;

public class FlappyBird {

	private Menu menu;
	private Level level; 
	
	private Bird bird;
	
	public FlappyBird(int w_width, int w_height) {
		level = new Level(w_width, w_height);
		
		setMenu(new MainMenu()); 
	}
	
	public void startGame() {
		setMenu(null);
		
		bird = new Bird(20, 340);
	}
	
	public void tick(InputHandler input, double delta) {
		boolean action = input.keys[KeyEvent.VK_SPACE];
		boolean clicked = input.mouseButtons[MouseEvent.BUTTON1];
		int mouseX = input.mouseX; 
		int mouseY = input.mouseY;
		
		
		level.tick();
		
		if(menu != null) {
			if(clicked) input.mouseButtons[MouseEvent.BUTTON1] = false;
			
			menu.tick(clicked, mouseX, mouseY, this);
		}
		
		if(bird != null) {
			if(clicked) input.mouseButtons[MouseEvent.BUTTON1] = false;
			
			bird.tick(action, clicked, delta);
		}
	}
	
	public void render(Graphics2D g) {
		level.render(g);
		
		if(menu != null) {
			menu.render(g);
		}
		
		if(bird != null) {
			bird.render(g);
		}
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
