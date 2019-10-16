package com.law.flappy.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	
	public static Sound wing = load("/sfx_wing.wav");
	public static Sound swooshing = load("/sfx_swooshing.wav");
	public static Sound hit = load("/sfx_hit.wav");
	
	private Clip clip;

	public static Sound load(String path) {
		Sound sound = new Sound();
		InputStream audioScr, bufferedIn;
		try {
			audioScr = Sound.class.getResourceAsStream(path);
			bufferedIn = new BufferedInputStream(audioScr);
			AudioInputStream stream = AudioSystem.getAudioInputStream(bufferedIn);
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			sound.clip = clip;
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return sound;
	}

	public void Play() {
		if (clip != null) {
			new Thread() {
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}
			}.start();
		}
	}

}