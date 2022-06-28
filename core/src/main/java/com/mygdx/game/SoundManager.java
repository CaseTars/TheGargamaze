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
	private static Sound playerTeleport;
    private static Sound end;
    private static Sound error;
    private static Sound victory;
	private static boolean musicOn = true;

	public static void loadSounds() {
	    String path = "sounds/";
        gameMusic         = Gdx.audio.newMusic(Gdx.files.internal(path + "interstellarTheme.mp3"));
	    doorOpening       = Gdx.audio.newSound(Gdx.files.internal(path + "MCDoorOpen.mp3"));
        doorClosing       = Gdx.audio.newSound(Gdx.files.internal(path + "MCDoorClose.mp3"));
	    wallHit           = Gdx.audio.newSound(Gdx.files.internal(path + "batendo.wav"));
	    crystalGetting    = Gdx.audio.newSound(Gdx.files.internal(path + "upandoXpMine.wav"));
	    playerDying       = Gdx.audio.newSound(Gdx.files.internal(path + "marioDeath.mp3"));
	    playerTeleport    = Gdx.audio.newSound(Gdx.files.internal(path + "endermanTeleport.mp3"));
	    end               = Gdx.audio.newSound(Gdx.files.internal(path + "end.mp3"));
        error             = Gdx.audio.newSound(Gdx.files.internal(path + "error.mp3"));
        victory           = Gdx.audio.newSound(Gdx.files.internal(path + "victory.mp3"));
	}
	
	public static void playGameMusic() {
	    musicOn = true;
		gameMusic.setLooping(true);
		gameMusic.play();
	}
	
	public static void stopGameMusic() {
		musicOn = false;
		gameMusic.stop();
		gameMusic.setLooping(false);

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
	    stopGameMusic();
		playerDying.play();	
	}
	
	public static void playPlayerTeleport() {
		playerTeleport.play(1, 1, 0);
	}
	
	public static void playEnd() {
        end.play();
    }
	
	public static void stopEnd() {
	    end.stop();
    }

    public static void playError() {
        error.play();
    }
    
    public static void playVictory() {
        stopGameMusic();
        victory.play();
    }

	public static void setMusic(boolean state) {
	    if(state)
	        playGameMusic();
	    else
	        stopGameMusic();
	}
	
	public static boolean getMusic() {
		return musicOn;
	}
	
	public static void disposeSounds() {
		gameMusic.dispose();
		doorOpening.dispose();
		doorClosing.dispose();
		playerDying.dispose();
		wallHit.dispose();
		playerTeleport.dispose();
		end.dispose();
		error.dispose();
	}
}
