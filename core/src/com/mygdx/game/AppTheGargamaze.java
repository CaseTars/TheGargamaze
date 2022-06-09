package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.interfaces.ITime;

public class AppTheGargamaze extends ApplicationAdapter {
	private View view;
	private ITime Tcase;
	private ITime Ttars;
	
	@Override
	public void create () {
		
		 Builder bob = new Builder();
		 try {
			 bob.build();
		 }
		 catch(Exception IOException){
			 //mensagem de erro e acabar o jogp
			 System.out.println(IOException.fillInStackTrace());
			 System.exit(1);
		 }
		 
		 view  = bob.getView();
		 Tcase = bob.getCase();
		 Ttars = bob.getTars();
	}

	@Override
	public void render () { 
		view.show();
		Tcase.update(Gdx.graphics.getDeltaTime());
		Ttars.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () { //view.dispose
		view.dispose();
	}
}
