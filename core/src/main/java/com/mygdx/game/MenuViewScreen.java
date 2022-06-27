package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.interfaces.IGameControl;

public class MenuViewScreen implements Screen{
		private IGameControl game;
		private OrthographicCamera camera;
	    private Viewport viewport;
	    private SpriteBatch batch;
	    private MenuControl menuControl;
	    private Rectangle play;
	    private Rectangle tutorial;
	    private Rectangle music;

	    private static Texture imgHappySantanche;
	    private static Texture imgTittle;
	    private static Texture imgPlay;
	    private static Texture imgTutorial;
	    private static Texture imgMusicOn;
	    private static Texture imgMusicOff;
	    private static Texture imgBackGround;

		public MenuViewScreen(AppTheGargamaze game) {
			this.game = game;
			
			batch = new SpriteBatch();

			camera = new OrthographicCamera();
	        camera.position.set(0, 0, 0);
	        camera.update();

	        viewport = new FitViewport(800, 480, camera);
			
			menuControl = new MenuControl();
			menuControl.connectMenu(this);
			
			float width = 115;
			float height = width*115f/366f;
			float posX = (800f/2)-width/2;
			
			play = new Rectangle(posX,160,width, height);
			tutorial = new Rectangle(posX, 160-5-height,width, height);
			music = new Rectangle(posX,160-10-(2*height),width, height);
			
	        Gdx.input.setInputProcessor(menuControl);
		}
		
		public static void loadImages() {
            imgHappySantanche   = new Texture(Gdx.files.internal("HappySantache.png"));  //check
            
            imgTittle           = new Texture(Gdx.files.internal("Tittle.png"));
            imgPlay             = new Texture(Gdx.files.internal("Play.png"));
            imgTutorial         = new Texture(Gdx.files.internal("Tutorial.png"));
            imgMusicOn          = new Texture(Gdx.files.internal("MusicOn.png"));
            imgMusicOff         = new Texture(Gdx.files.internal("MusicOff.png"));
            imgBackGround       = new Texture(Gdx.files.internal("menu.jpeg"));
		}

		@Override
		public void render(float delta) {
			ScreenUtils.clear(0, 0, 0, 1);
			camera.update();
			batch.setProjectionMatrix(camera.combined);

			batch.begin();
			
			batch.draw(imgBackGround, 0, 0, 960, 480);
			batch.draw(imgTittle, 200, 250, 400, 200);
			batch.draw(imgPlay, play.x, play.y, play.width, play.height);
			batch.draw(imgTutorial, tutorial.x, tutorial.y, tutorial.width, tutorial.height);
			
			boolean showMusicOn = SoundManager.getMusic();
			if(showMusicOn) batch.draw(imgMusicOn, music.x, music.y, music.width, music.height);
			else if(!showMusicOn) batch.draw(imgMusicOff, music.x, music.y, music.width, music.height);

			batch.end();
	        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); 
		}
		
		public void unproject(Vector3 touchPos) {
			viewport.unproject(touchPos);
		}
		
		public boolean playContains(float posX, float posY) { 
			return play.contains(posX, posY);
		}
		
		public boolean tutorialContains(float posX, float posY) { 
			return tutorial.contains(posX, posY);
		}
		
		public boolean musicContains(float posX, float posY) { 
			return music.contains(posX, posY);
		}

		public void play() {
		    game.setScreen(1);
		}
		
		public void showTutorial() {
			game.setScreen(3);
		}
		
		@Override
	    public void resize(int width, int height) {
	        viewport.update(width, height);
	    }
	
		@Override
		public void pause() {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void dispose() {
	        imgHappySantanche.dispose();
	        imgTittle.dispose();
	        imgPlay.dispose();
	        imgTutorial.dispose();
	        imgMusicOn.dispose();
	        imgMusicOff.dispose();
	        imgBackGround.dispose();
	        batch.dispose();
		}
	
		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}

}
