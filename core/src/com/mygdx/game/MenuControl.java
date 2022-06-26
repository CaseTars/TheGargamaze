package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class MenuControl implements InputProcessor {
	private MenuViewScreen menu;
	
	public void connectMenu(MenuViewScreen menu) {
		this.menu = menu;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
					
			Vector3 touchPos = new Vector3();
			touchPos.set(screenX, screenY, 0);
			
			menu.unproject(touchPos);
			float posX = touchPos.x;
			float posY = touchPos.y;
						
			if(menu.playContains(posX, posY)) menu.play();
			else if(menu.tutorialContains(posX, posY)) menu.showTutorial();
		
			else if(menu.musicContains(posX, posY)) {
			    boolean state = SoundManager.getMusic();
				SoundManager.setMusic(!state);
			}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}
