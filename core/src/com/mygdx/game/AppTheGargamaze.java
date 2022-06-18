package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.interfaces.ITime;

public class AppTheGargamaze extends Game {
	private View view;
	//private ITime Tcase;
	//private ITime Ttars;
   // private ITime control;
   // private float t;
	
    SpriteBatch batch;
    
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		this.setScreen(new MenuViewScreen(this));
	}
	
	public void create2() {
		
		 Builder bob = new Builder();
		 try {
			 bob.build();
		 }
		 catch(Exception Exception){
			 //mensagem de erro e acabar o jogp
			 System.out.println(Exception.fillInStackTrace());
			 System.exit(1);
		 }
		 
		 view  = bob.getView();
		 //Tcase = bob.getCase();
		 //Ttars = bob.getTars();
		 //control = bob.getControl();
		 
		 this.setScreen(view);
	}

	@Override
	public void render () { 
		super.render();
	}
	
	@Override
	public void dispose () { //view.dispose
		view.dispose();
		SoundManager.disposeSounds();
		batch.dispose();
	}
}
