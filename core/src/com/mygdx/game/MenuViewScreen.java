package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuViewScreen implements Screen{

		private AppTheGargamaze game;
		private OrthographicCamera camera;
	    private Viewport viewport;
	    private SpriteBatch batch;

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

//			camera.setToOrtho(false, 800, 480);
	        viewport = new FitViewport(800, 480, camera);
	        
			imgHappySantanche = new Texture(Gdx.files.internal("HappySantache.png"));  //check
			imgTittle = new Texture(Gdx.files.internal("Tittle.png")); 
			imgPlay = new Texture(Gdx.files.internal("Play.png")); 
			imgTutorial = new Texture(Gdx.files.internal("Tutorial.png")); 
			imgMusicOn = new Texture(Gdx.files.internal("MusicaOn.png")); 
			imgMusicOff = new Texture(Gdx.files.internal("MusicaOff.png")); 
			imgBackGround = new Texture(Gdx.files.internal("fundo.jpeg")); 
	        SoundManager.loadSounds();
			SoundManager.playGameMusic();
		}

		@Override
		public void render(float delta) {
			ScreenUtils.clear(0, 0, 0, 1);
			camera.update();
			batch.setProjectionMatrix(camera.combined);

			batch.begin();
			
			batch.draw(imgBackGround, 0, 0, 960, 480);
			batch.draw(imgTittle, 200, 250, 400, 200);
			batch.draw(imgPlay, 300, 150, 200, 150);
			batch.draw(imgTutorial, 300, 100, 200, 150);
			if(SoundManager.getMusic()) batch.draw(imgMusicOn, 300, 50, 200, 150);
			else if(!SoundManager.getMusic()) batch.draw(imgMusicOff, 300, 50, 200, 150);


			if (Gdx.input.isTouched()) {
				
				Vector3 touchPos = new Vector3();
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touchPos);
				
				float posX = touchPos.x;
				float posY = touchPos.y;
				
				if(posX > 300 && posX < 500 && posY > 200 && posY < 350) { //play
					game.create2();
					dispose();
				}
				else if(posX > 300 && posX < 500 && posY > 150 && posY < 200)  //tutorial
					batch.draw(imgHappySantanche, 0, 0, 500, 280);
				else if(posX > 300 && posX < 500 && posY > 100 && posY < 140 && SoundManager.getMusic()) { 
					SoundManager.setMusic(false);
					SoundManager.stopGameMusic();
					batch.draw(imgMusicOff, 300, 50, 200, 150);
				}
				else if(posX > 300 && posX < 500 && posY > 100 && posY < 140 && !SoundManager.getMusic()) {
					SoundManager.setMusic(true);
					SoundManager.playGameMusic();
					batch.draw(imgMusicOn, 300, 50, 200, 150);
				}
			}
			batch.end();
	        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.

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
        batch.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}