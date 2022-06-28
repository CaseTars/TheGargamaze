package com.mygdx.game.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SoundManager;
import com.mygdx.game.exceptions.AssembleError;
import com.mygdx.game.interfaces.ITime;
import com.mygdx.game.screens.end.EndScreen;
import com.mygdx.game.screens.game.Builder;
import com.mygdx.game.screens.game.view.View;
import com.mygdx.game.screens.menu.MenuViewScreen;
import com.mygdx.game.screens.story.StoryScreen;
import com.mygdx.game.screens.tutorial.BuilderTutorial;

public class AppTheGargamaze extends Game implements IGame {
	private View view;
	private boolean running = false;
    private boolean sucess;
	private Array<ITime> updateTs = new Array<ITime>();
	    
	@Override
	public void create () {
        SoundManager.loadSounds();
        SoundManager.playGameMusic();
        MenuViewScreen.loadImages();
        createMenu();
	}
	
	public void createMenu() {
        this.setScreen(new MenuViewScreen(this));
	}
    
    public void createStory() {
        this.setScreen(new StoryScreen(this));
    }
	
	public void createGame() {
		 Builder bob = new Builder(this);
		 try {
			 bob.build();
		 }
		 catch(Exception Exception){
			 //mensagem de erro e acabar o jogp
			 Exception.printStackTrace();
			 System.exit(1);
		 }
		 
		 updateTs.clear();
		 updateTs.add(bob.getCase());
         updateTs.add(bob.getTars());
         updateTs.add(bob.getControl());
         updateTs.add(bob.getBH());
         
         view  = bob.getView();
         
		 this.setScreen(view);
         running = true;
	}

    public void createTutorial() {
         Builder bob = new BuilderTutorial(this);
         
         try {
             bob.build();
         }
         catch(AssembleError Exception){
        	 System.out.println("Error while creating game.");
        	 System.exit(1);
         }
         
         updateTs.clear();
         updateTs.add(bob.getCase());
         updateTs.add(bob.getTars());
         updateTs.add(bob.getControl());
         
         view  = bob.getView();
         
         this.setScreen(view);
         running = true;
    }
    
	public void render () {
		super.render();
		
		float delta = Gdx.graphics.getDeltaTime();
		if(running)
		    for(ITime updateT : updateTs)
    			updateT.update(delta);
	}
	
	@Override
	public void dispose () { 
		SoundManager.disposeSounds();
		if(view != null) {
			view.dispose();
		}
	}

    @Override
    public void gameOver(boolean sucess) {
        running = false;
        this.sucess = sucess;
        view.fadeOut();
    }

    @Override
    public void gameOverContinue() {
        this.setScreen(new EndScreen(this, sucess));
    }

    @Override
    public void setScreen(int i) {
        if(i==0)
            createMenu();
        else if(i==1)
            createStory();
        else if(i==2)
            createGame();
        else if(i==3)
            createTutorial();
    }
}
