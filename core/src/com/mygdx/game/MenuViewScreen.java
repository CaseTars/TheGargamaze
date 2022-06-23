package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuViewScreen implements Screen{

		private AppTheGargamaze game;
		private OrthographicCamera camera;
	    private Viewport viewport;
	    private SpriteBatch batch;
	    private MenuControl menuControl;
	    private boolean showTutorial;
	    private boolean showMusicOn;

	    private static Texture imgHappySantanche;
	    private static Texture imgTittle;
	    private static Texture imgPlay;
	    private static Texture imgTutorial;
	    private static Texture imgMusicOn;
	    private static Texture imgMusicOff;
	    private static Texture imgBackGround;

		public MenuViewScreen(AppTheGargamaze gam) {
			game = gam;
			
			batch = new SpriteBatch();

			camera = new OrthographicCamera();
	        camera.position.set(0, 0, 0);
	        camera.update();

	        viewport = new FitViewport(800, 480, camera);
	        
			imgHappySantanche = new Texture(Gdx.files.internal("HappySantache.png"));  //check
			imgTittle = new Texture(Gdx.files.internal("Tittle.png")); 
			imgPlay = new Texture(Gdx.files.internal("Play.png")); 
			imgTutorial = new Texture(Gdx.files.internal("Tutorial.png")); 
			imgMusicOn = new Texture(Gdx.files.internal("MusicaOn.png")); 
			imgMusicOff = new Texture(Gdx.files.internal("MusicaOff.png")); 
			imgBackGround = new Texture(Gdx.files.internal("fundo.jpeg")); 
			showTutorial = false;
			showMusicOn = true;
			
	        SoundManager.loadSounds();
			SoundManager.playGameMusic();
			
			menuControl = new MenuControl();
			menuControl.connectMenu(this);
			
	        Gdx.input.setInputProcessor(menuControl);
		}

		@Override
		public void render(float delta) {
			ScreenUtils.clear(0, 0, 0, 1);
			camera.update();
			batch.setProjectionMatrix(camera.combined);

			batch.begin();
			
			float bSize = 115;
			batch.draw(imgBackGround, 0, 0, 960, 480);
			batch.draw(imgTittle, 200, 250, 400, 200);
			batch.draw(imgPlay, (800f/2)-bSize/2, 210, bSize, bSize*115f/336f);
			batch.draw(imgTutorial, (800f/2)-bSize/2, 210-bSize*115f/336f, bSize, bSize*115f/336f);
			
			if(showMusicOn) batch.draw(imgMusicOn, 300, 50, 200, 150);
			else if(!showMusicOn) batch.draw(imgMusicOff, 300, 50, 200, 150);
			
			if(showTutorial) { //tutorial
				batch.draw(imgHappySantanche, 0, 0, 500, 280); 
				showTutorial = false;
			}

			
			
//				\
//				Vector3 touchPos = new Vector3();
//				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//				camera.unproject(touchPos);
//				
			batch.end();
	        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); 
		}
		
		public void camera(Vector3 touchPos) {
			viewport.unproject(touchPos);
		}

		public void play() {
			game.create2();
			dispose();
		}
		
		public void showTutorial() {
			showTutorial = true;
		}
		
		public void showMusicOn() {
			showMusicOn = true;
		}
		
		public void showMusicOff() {
			showMusicOn = false;
		}
		
		@Override
		public void resize(int width, int height) {
			// TODO Auto-generated method stub
			
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
	//      batch.dispose();
	
		}
	
		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}

}
