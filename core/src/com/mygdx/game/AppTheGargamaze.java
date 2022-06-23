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
	private boolean running;
	private ITime Tcase;
	private ITime Ttars;
    private ITime control;
	    
	@Override
	public void create () {
		running = false;
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
		 
		 running = true;
		 view  = bob.getView();
		 Tcase = bob.getCase();
		 Ttars = bob.getTars();
		 control = bob.getControl();
		 
		 this.setScreen(view);
	}

	public void render () {
		super.render();
		
		float delta = Gdx.graphics.getDeltaTime();
		if(running) {
			Tcase.update(delta);
			Ttars.update(delta);
			control.update(delta);
		}
	}
	
	@Override
	public void dispose () { 
		SoundManager.disposeSounds();
		if(view != null) {
			view.dispose();
		}
	}
}
