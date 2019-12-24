package com.law.flappy;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import com.law.flappy.entities.Bird;
import com.law.flappy.entities.Pipe;
import com.law.flappy.input.InputHandler;
import com.law.flappy.menu.GameOverMenu;
import com.law.flappy.menu.MainMenu;
import com.law.flappy.menu.Menu;
import com.law.flappy.util.Score;
import com.law.flappy.util.Sound;

public class FlappyBird {

	private Menu menu;
	private Level level; 
	private Score score;
	
	private Bird bird;
	private ArrayList<Pipe> pipes = new ArrayList<Pipe>();
	private Pipe nextPipe;
	private double pipeSpawnTime = 100;
	private double time = 0;
	
	private boolean gameStarted = false;
	private boolean gameOver = false;
	
	private boolean showHitboxes = false;
	
	private Random random = new Random();
	
	public FlappyBird(int w_width, int w_height) {
		level = new Level(w_width, w_height);
		
		setMenu(new MainMenu()); 
	}
	
	public void startGame() {
		setMenu(null);
		gameStarted = true;
		
		score = new Score();
		bird = new Bird(30, 340);
		generatePipes();
		
		nextPipe = pipes.get(0);
	};
	
	public void resetGame() {
		gameStarted = false;
		gameOver = false;
		bird = null;
		
		pipes.clear();
		
		setMenu(new MainMenu());
	}
	
	public void retry() {
		gameStarted = false;
		gameOver = false;
		bird = null;
		
		pipes.clear();
		
		startGame();
	}
	
	public void gameOver() {
		if(!gameOver && !Sound.muted) Sound.hit.Play();
		this.gameOver = true;
		
		setMenu(new GameOverMenu(score));
	}
	
	public void generatePipes() {
		Pipe pipeDown, pipeUp;
		Pipe lastPipe = null;
		int pipeY = 0;
		while(pipeY < 50) {
			pipeY = (random.nextInt(430) + 1);
		}
		
		pipeY = pipeY * -1;
		
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
	
	public void checkCollisions() {
		if(bird.getHitbox().collision(level.getGroundHb())) {	
			bird.stopMovement();
	
			if(!gameOver) gameOver();
		}
		
		for(Pipe pipe: pipes) {
			if(bird.getHitbox().collision(pipe.getHitbox())) {
				if(!gameOver) gameOver();
			}
		}
	}
	
	public void checkScore() {
		if(bird.getX() > nextPipe.getX() + 30) {
			score.increase();
			nextPipe = pipes.get(pipes.indexOf(nextPipe) + 2);
		}
	}
	
	public void toggleShowHb() {
		bird.showHitbox(!this.showHitboxes);
		level.showHitbox(!this.showHitboxes);
		
		this.showHitboxes = !this.showHitboxes;
	}
	
	public void tick(InputHandler input, double delta) {
		/*
		 * INPUT
		 */
		boolean action = input.keys[KeyEvent.VK_SPACE];
		boolean H = input.keys[KeyEvent.VK_H];
		boolean clicked = input.mouseButtons[MouseEvent.BUTTON1];
		int mouseX = input.mouseX; 
		int mouseY = input.mouseY;
		
		
		/*
		 * LEVEL 
		 */
		level.tick(delta, gameStarted, gameOver, pipes);
		
		/*
		 * MENU
		 */
		if(menu != null) {
			if(clicked) input.mouseButtons[MouseEvent.BUTTON1] = false;
			
			menu.tick(delta, clicked, mouseX, mouseY, this);
		}
		
		/*
		 * GAME
		 */
		if(gameStarted) {
			
			if(clicked) input.mouseButtons[MouseEvent.BUTTON1] = false;
			if(H)  {
				input.keys[KeyEvent.VK_H] = false;
				toggleShowHb();
			}
			
			checkScore();
			checkCollisions();
			bird.tick(action, clicked, gameOver, delta);
		}
		
		/*
		 * PIPES
		 */
		time += delta;
		if(time > pipeSpawnTime && gameStarted) {
			cleanPipes();
			generatePipes();
			time = 0;
		}
	}
	
	public void render(Graphics2D g) {
		
		/*
		 * LEVEL
		 */
		level.render(g, gameStarted, pipes);
		
		/*
		 * SCORE
		 */
		if(gameStarted && !gameOver) {
			score.render(g, false);
		}
		
		/*
		 * GAME
		 */
		if(gameStarted) {
			bird.render(g);
		}
		
		/*
		 * MENU
		 */
		if(menu != null) {
			menu.render(g);
		}
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
