package com.law.flappy;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


import com.law.flappy.input.InputHandler;

public class GamePanel extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Flappy Bird";
	private static final int WIDTH = 420;
	private static final int HEIGHT = 720;

	private boolean running;
	private Thread thread;

	private InputHandler input;

	private FlappyBird game;

	public GamePanel() {
		Dimension windowSize = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(windowSize);

		setFocusable(true);

		input = new InputHandler();
		addKeyListener(input);
		addMouseListener(input);

		game = new FlappyBird(WIDTH, HEIGHT);
	}

	public void start() {
		if (running)
			return;

		System.out.println("Starting...");
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	public void stop() {
		if (!running)
			return;

		System.out.println("Stopping...");
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void tick(double delta) {
		game.tick(input, delta);
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics2D g = (Graphics2D) bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		game.render(g);

		g.dispose();
		bs.show();
	}

	public void run() {

		int fps = 0;
		long lastFpsTime = 0;
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		while (running) {
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				lastFpsTime = 0;
				fps = 0;
			}

			tick(delta);

			render();

			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
			}

		}
	}

	public static void main(String[] args) {
		JFrame window = new JFrame(TITLE);
		GamePanel engine = new GamePanel();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(engine);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);

		engine.start();

	}

}
