package com.law.flappy;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.law.flappy.entities.Bird;
import com.law.flappy.entities.Pipe;
import com.law.flappy.input.InputHandler;
import com.law.flappy.menu.MainMenu;
import com.law.flappy.menu.Menu;

public class FlappyBird {

	private Menu menu;
	private Level level; 
	
	private Bird bird;
	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private double pipeSpawnTime = 100;
	private double time = 0;
	
	private boolean gameStarted = false;
	private Random random = new Random();
	
	public FlappyBird(int w_width, int w_height) {
		level = new Level(w_width, w_height);
		
		setMenu(new MainMenu()); 
	}
	
	public void startGame() {
		setMenu(null);
		gameStarted = true;
		
		bird = new Bird(30, 340);
		generatePipes();
	}
	
	public void generatePipes() {
		Pipe pipeDown, pipeUp;
		Pipe lastPipe = null;
		int pipeY = 0;
		while(pipeY < 20) {
			pipeY = random.nextInt(400) + 1;
		}
		
		if(pipes.size() != 0) {
			lastPipe = pipes.get(pipes.size() - 1);
		} 
		
		if(lastPipe == null) {
			pipeDown = new Pipe(430, pipeY, 0);
			pipeUp = new Pipe(430, pipeY + 650, 1);
		} else {
			pipeDown = new Pipe(lastPipe.getX() + 220, pipeY, 0);
			pipeUp = new Pipe(lastPipe.getX() + 220, pipeY + 650, 1);
		}
		
		pipes.add(pipeDown);
		pipes.add(pipeUp);
	}
	
	public void cleanPipes() {
		if(gameStarted && pipes.size() > 0) {
			for(int i = 0; i < pipes.size(); i++) {
				if(pipes.get(i).getX() < -105) pipes.remove(i);
			}
		}
	}
	
	public void tick(InputHandler input, double delta) {
		boolean action = input.keys[KeyEvent.VK_SPACE];
		boolean clicked = input.mouseButtons[MouseEvent.BUTTON1];
		int mouseX = input.mouseX; 
		int mouseY = input.mouseY;
		
		
		time += delta;
		if(time > pipeSpawnTime && gameStarted) {
			cleanPipes();
			generatePipes();
			time = 0;
		}
		
		level.tick(delta, gameStarted, pipes);
		
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
		
		level.render(g, gameStarted, pipes);
		
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
