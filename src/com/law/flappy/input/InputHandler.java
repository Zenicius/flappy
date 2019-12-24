package com.law.flappy.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements KeyListener, MouseListener {

	public boolean[] keys = new boolean[65536];
	public boolean[] mouseButtons = new boolean[4];
	public int mouseX, mouseY;

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < keys.length) {
			keys[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < keys.length) {
			keys[keyCode] = false;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int mouseCode = e.getButton();
		if(mouseCode > 0 && mouseCode < mouseButtons.length) {
			mouseX = e.getX();
			mouseY = e.getY();
			mouseButtons[mouseCode] = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int mouseCode = e.getButton();
		if(mouseCode > 0 && mouseCode < mouseButtons.length) {
			mouseX = e.getX();
			mouseY = e.getY();
			mouseButtons[mouseCode] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}