package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private static Music gameMusic;
	private static Sound doorOpening;
	private static Sound doorClosing;
	private static Sound wallHit;
	private static Sound crystalGetting;
	private static Sound playerDying;


	
	public static void loadSounds() {
	     doorOpening = Gdx.audio.newSound(Gdx.files.internal("MC Door Open .mp3"));
	     gameMusic = Gdx.audio.newMusic(Gdx.files.internal("interstellarTheme.mp3"));
	     doorClosing = Gdx.audio.newSound(Gdx.files.internal("MC Door Close2.mp3"));
	     wallHit = Gdx.audio.newSound(Gdx.files.internal("batendo2.wav"));
	     crystalGetting = Gdx.audio.newSound(Gdx.files.internal("upandoXpMine.wav"));
//	     crystalGetting = Gdx.audio.newSound(Gdx.files.internal("Super-Mario-Bros-1-Up.wav"));

	     playerDying = Gdx.audio.newSound(Gdx.files.internal("Mario Death.mp3"));
	}
	
	public static void playGameMusic() {
		gameMusic.setLooping(true);
		gameMusic.play();
	}
	
	public static void playWallHit() {
		wallHit.play(1);
	}
	
	public static void playDoorOpening() {
		doorOpening.play();
	}
	
	public static void playDoorClosing() {
		doorClosing.play();
	}
	
	public static void playCrystalGetting() {
		crystalGetting.play(1f, 1.5f, 0f);
	}
	
	public static void playPlayerDying() {
		playerDying.play();	
	}

	
	public static void disposeSounds() {
		gameMusic.dispose();
		doorOpening.dispose();
		doorClosing.dispose();
		playerDying.dispose();
		wallHit.dispose();
	}
}
